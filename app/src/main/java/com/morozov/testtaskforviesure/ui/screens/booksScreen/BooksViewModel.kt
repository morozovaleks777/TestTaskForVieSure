package com.morozov.testtaskforviesure.ui.screens.booksScreen

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morozov.common.ApiResult
import com.morozov.common.LoadableUiState
import com.morozov.common.toLoadableUiState
import com.morozov.common.utils.toCustomDateFormat
import com.morozov.domain.domain.GetBooksUseCase
import com.morozov.domain.domain.models.Book
import com.morozov.domain.domain.roomUseCases.GetBooksUseCaseFromRoom
import com.morozov.domain.domain.roomUseCases.InsertBookUseCase
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
    val errorMessage: String? = null,
    val title: String = "Books"
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
                        author = action.book.author,
                        description = action.book.description,
                        id = action.book.id ,
                        image = action.book.image,
                        releaseDate = action.book.releaseDate,
                        title = action.book.title,
                        titlee = action.book.titlee,
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
                            isRefreshing = false,
                            title = "Books from api"
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
                        _uiState.update {
                            it.copy(
                                booksPageState = localBooks.toLoadableUiState(),
                                isRefreshing = false,
                                errorMessage = "Failed to sync data. Showing locally stored books.",
                                title = "Books from database"
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

        repeat(retries - 1) { _->
            try {
                val apiResult = block()
                if (apiResult.success && apiResult.data != null) {
                    return Result.success(apiResult.data)
                }
            } catch (e: Exception) {
                delay(initialDelay)

            }
        }

        return try {
            val apiResult = block()
            if (apiResult.success && apiResult.data != null) {
                Result.success(apiResult.data)
            } else {
                Result.failure(
                    IOException(
                        apiResult.error?.message ?: "Unknown error on final attempt"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

