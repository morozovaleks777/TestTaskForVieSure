package com.morozov.testtaskforviesure.ui.screens.booksScreen

import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morozov.common.ApiResult
import com.morozov.common.LoadableUiState
import com.morozov.domain.domain.models.Book
import com.morozov.common.toLoadableUiState
import com.morozov.domain.domain.GetBooksUseCase
import com.morozov.domain.domain.roomUseCases.InsertBookUseCase

import com.morozov.testtaskforviesure.domain.roomUseCases.GetBooksUseCaseFromRoom
import com.morozov.common.utils.toCustomDateFormat
import com.morozov.navigation.AboutMe
import com.morozov.navigation.BookDetail
import com.morozov.navigation.NavigationManager
import com.morozov.navigation.goToAboutMe
import com.morozov.navigation.goToBooksDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@Stable
data class BooksUiState(
    val booksPageState: LoadableUiState<List<Book>> = LoadableUiState.Loading(),
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null
)
@Stable
sealed class BooksAction {
    data object AboutMe : BooksAction()
    data class ShowToast(val message: String) : BooksAction()
    data class GetBooksData(val isRefreshing: Boolean = false) : BooksAction()
    data class GoToBookDetail(val book: Book) : BooksAction()
}

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val getBooksUseCase: GetBooksUseCase,
    private val insertBookUseCase: InsertBookUseCase,
    private val getBooksUseCaseFromRoom: GetBooksUseCaseFromRoom,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BooksUiState())
    val uiState: StateFlow<BooksUiState> = _uiState.asStateFlow()

    val send: (BooksAction) -> Unit = { action ->
        when (action) {
            is BooksAction.ShowToast -> {}
            is BooksAction.GetBooksData -> fetchBooks(action.isRefreshing)
            is BooksAction.GoToBookDetail -> {
                navigationManager.goToBooksDetail(
                    bookDetail = BookDetail(
                        author = action.book.author.orEmpty(),
                        description = action.book.description.orEmpty(),
                        id = action.book.id ?: 0,
                        image = action.book.image.orEmpty(),
                        releaseDate = action.book.releaseDate.orEmpty(),
                        title = action.book.title.orEmpty(),
                        titlee = action.book.titlee.orEmpty(),
                    )
                )
            }

            is BooksAction.AboutMe -> navigationManager.goToAboutMe(AboutMe)

        }
    }

    // Fetch books data with retry and local fallback mechanism
    private fun fetchBooks(isRefreshing: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(
                    isRefreshing = isRefreshing,
                    errorMessage = null
                )
            }

            val result = retryWithBackoff(retries = 3, initialDelay = 2000L) {
                getBooksUseCase.invoke()
            }

            result.fold(
                onSuccess = { books ->
                    Log.d("post", "fetchBooks: onsucces")
                    // Insert books into the local database
                    val sortedBooks = books?.sortedBy { book ->
                        book.releaseDate.toCustomDateFormat()
                    }

                    sortedBooks?.forEach { book ->
                            insertBookUseCase(book)
                    }
                    _uiState.update {
                        it.copy(
                            booksPageState = (books ?: emptyList()).toLoadableUiState(),
                            isRefreshing = false
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            booksPageState = LoadableUiState.Error(error.message),
                            isRefreshing = false,
                            errorMessage = "Failed to sync data. Loading locally stored books."
                        )
                    }
                    delay(3000)
                    // Load locally stored books in case of failure
                    getBooksUseCaseFromRoom.invoke().collect { localBooks ->
                        Log.d("post", "fetchBooks: local  $localBooks")
                        _uiState.update {
                            Log.d("rost", "fetchBooks: updatestate")
                            it.copy(
                                booksPageState = localBooks.toLoadableUiState(),
                                isRefreshing = false,
                                errorMessage = "Failed to sync data. Showing locally stored books."
                            )
                        }
                    }
                }
            )
        }
    }

    private suspend fun <T> retryWithBackoff(
        retries: Int,
        initialDelay: Long,
        block: suspend () -> ApiResult<T>
    ): Result<T?> {
        var currentDelay = initialDelay

        repeat(retries - 1) { attempt ->
            Log.d("rost", "retryWithBackoff: attempt ${attempt + 1}")
            try {
                val apiResult = block()
                if (apiResult.success && apiResult.data != null) {
                    Log.d("rost", "retryWithBackoff: success on attempt ${attempt + 1}")
                    return Result.success(apiResult.data)
                } else {
                    Log.d("rost", "retryWithBackoff: block returned unsuccessful ApiResult on attempt ${attempt + 1}")
                    throw IOException(apiResult.error?.message ?: "Unknown error")
                }
            } catch (e: Exception) {
                Log.d("rost", "retryWithBackoff: failed on attempt ${attempt + 1}, waiting for $currentDelay ms")
                delay(currentDelay)

            }
        }

        return try {
            Log.d("rost", "retryWithBackoff: final attempt")
            val apiResult = block()
            if (apiResult.success && apiResult.data != null) {
                Result.success(apiResult.data)
            } else {
                Result.failure(IOException(apiResult.error?.message ?: "Unknown error on final attempt"))
            }
        } catch (e: Exception) {
            Log.d("rost", "retryWithBackoff: final attempt failed")
            Result.failure(e)
        }
    }



}

