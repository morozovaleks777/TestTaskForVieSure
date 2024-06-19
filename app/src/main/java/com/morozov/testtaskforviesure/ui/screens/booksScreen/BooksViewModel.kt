package com.morozov.testtaskforviesure.ui.screens.booksScreen

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morozov.testtaskforviesure.data.LoadableUiState
import com.morozov.testtaskforviesure.data.toLoadableUiState
import com.morozov.testtaskforviesure.domain.Book
import com.morozov.testtaskforviesure.domain.GetBooksUseCase
import com.morozov.testtaskforviesure.navigation.AboutMe
import com.morozov.testtaskforviesure.navigation.BookDetail
import com.morozov.testtaskforviesure.navigation.NavigationManager
import com.morozov.testtaskforviesure.navigation.goToAboutMe
import com.morozov.testtaskforviesure.navigation.goToBooksDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
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
    // val goToBookDetail: LoadableUiState<Book> = LoadableUiState.Loading()
)

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

    ) : ViewModel() {


    private val _uiState = MutableStateFlow(BooksUiState())
    val uiState: StateFlow<BooksUiState> = _uiState.asStateFlow()

    val send: (BooksAction) -> Unit = { action ->
        when (action) {

            is BooksAction.ShowToast -> {}
            is BooksAction.GetBooksData -> fetchBooks(action.isRefreshing)
            is BooksAction.GoToBookDetail -> {
                navigationManager.goToBooksDetail(
                    bookDetail = BookDetail
                        (
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
                throw IllegalArgumentException("Data for insticator WebView should be available")
            }

        }

    }

    private fun fetchBooks(isRefreshing: Boolean) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isRefreshing = isRefreshing
                )
            }

                val books = async {
                    getBooksUseCase.invoke()
                }.await()

                _uiState.update {
                    it.copy(
                        booksPageState = books.toLoadableUiState(),
                        isRefreshing = false
                        )
                }

            }
        }
    }
