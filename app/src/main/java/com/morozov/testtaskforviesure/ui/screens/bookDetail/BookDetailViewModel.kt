package com.morozov.testtaskforviesure.ui.screens.bookDetail

import androidx.lifecycle.ViewModel
import com.morozov.navigation.NavigationManager
import com.morozov.navigation.goBack
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


sealed class BookDetailAction {
    data class ShowToast(val message: String) : BookDetailAction()
    data object GoBack : BookDetailAction()

}

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
) : ViewModel() {


    val send: (BookDetailAction) -> Unit = { action ->
        when (action) {
            is BookDetailAction.ShowToast -> {}
            is BookDetailAction.GoBack -> {
                navigationManager.goBack()
            }
        }
    }
}