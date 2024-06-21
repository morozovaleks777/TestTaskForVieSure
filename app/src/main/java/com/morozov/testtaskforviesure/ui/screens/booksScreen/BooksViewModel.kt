package com.morozov.testtaskforviesure.ui.screens.booksScreen

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morozov.testtaskforviesure.data.LoadableUiState
import com.morozov.testtaskforviesure.data.toLoadableUiState
import com.morozov.testtaskforviesure.domain.Book
import com.morozov.testtaskforviesure.domain.GetBooksUseCase
import com.morozov.testtaskforviesure.domain.roomUseCases.GetBooksUseCaseFromRoom
import com.morozov.testtaskforviesure.domain.roomUseCases.InsertBookUseCase
import com.morozov.testtaskforviesure.navigation.AboutMe
import com.morozov.testtaskforviesure.navigation.BookDetail
import com.morozov.testtaskforviesure.navigation.NavigationManager
import com.morozov.testtaskforviesure.navigation.goToAboutMe
import com.morozov.testtaskforviesure.navigation.goToBooksDetail
import com.morozov.testtaskforviesure.ui.utils.toCustomDateFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
            else -> {
                throw IllegalArgumentException("Data  should be available")
            }
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
                    val sortedBooks = books.data?.sortedBy { book ->
                        book.releaseDate.toCustomDateFormat()
                    }

                    sortedBooks?.forEach { book ->
                        viewModelScope.launch {
                            insertBookUseCase(book)
                        }
                    }
                    _uiState.update {
                        it.copy(
                            booksPageState = books.toLoadableUiState(),
                            isRefreshing = false
                        )
                    }
                },
                onFailure = { error ->
                    // Load locally stored books in case of failure
                    getBooksUseCaseFromRoom.invoke().collect { localBooks ->
                        Log.d("post", "fetchBooks: local  $localBooks")
                        _uiState.update {
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

    // Retry mechanism with backoff delay
    private suspend fun <T> retryWithBackoff(
        retries: Int,
        initialDelay: Long,
        block: suspend () -> T
    ): Result<T> {
        repeat(retries - 1) {
            try {
                return Result.success(block())
            } catch (e: Exception) {
                delay(initialDelay)
            }
        }
        return try {
            Result.success(block())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

