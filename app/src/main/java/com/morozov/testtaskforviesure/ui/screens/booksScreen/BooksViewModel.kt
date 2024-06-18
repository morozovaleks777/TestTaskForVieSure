package com.morozov.testtaskforviesure.ui.screens.booksScreen

import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morozov.testtaskforviesure.data.LoadableUiState
import com.morozov.testtaskforviesure.data.toLoadableUiState
import com.morozov.testtaskforviesure.domain.Book
import com.morozov.testtaskforviesure.domain.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@Stable
data class BooksUiState(
    val booksPageState: LoadableUiState<List<Book>> = LoadableUiState.Loading(),
    val goToBookDetail: LoadableUiState<Book> = LoadableUiState.Loading()
    )

sealed class BooksAction {

    data class ShowToast(val message: String) : BooksAction()
    data class GetBooksData(val isRefreshing: Boolean = false) : BooksAction()
    data class GoToBookDetail(val book: Book): BooksAction()

}


@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow(BooksUiState())
    val uiState: StateFlow<BooksUiState> = _uiState.asStateFlow()

    val send: (BooksAction) -> Unit = { action ->
        when (action) {

            is BooksAction.ShowToast -> {}
            is BooksAction.GetBooksData -> fetchBooks()
            is BooksAction.GoToBookDetail -> {

            }
            else -> {
                throw IllegalArgumentException("Data for insticator WebView should be available")
            }

        }

    }

    private fun fetchBooks() {
        viewModelScope.launch {


            val books = async {
                getBooksUseCase.invoke()
            }.await()

            _uiState.update {
                it.copy(
                    booksPageState = books.toLoadableUiState(),

                    )

            }
            Log.d("post", "fetchBooks:${books.toLoadableUiState()} ")
        }
    }
}