package com.morozov.testtaskforviesure.ui.screens.bookDetail

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import com.morozov.common.LoadableUiState
import com.morozov.domain.domain.models.Book
import com.morozov.navigation.NavigationManager
import com.morozov.navigation.goBack
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@Stable
data class BookDetailUiState(
    val bookDetailState: LoadableUiState<List<Book>> = LoadableUiState.Loading(),

    )

sealed class BookDetailAction {
    data class ShowToast(val message: String) : BookDetailAction()
    data object GoBack : BookDetailAction()

}

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookDetailUiState())
    val uiState: StateFlow<BookDetailUiState> = _uiState.asStateFlow()

    val send: (BookDetailAction) -> Unit = { action ->
        when (action) {
            is BookDetailAction.ShowToast -> {}
            is BookDetailAction.GoBack -> {
                navigationManager.goBack()
            }

            else -> {
                throw IllegalArgumentException("Data for insticator WebView should be available")
            }

        }

    }

}