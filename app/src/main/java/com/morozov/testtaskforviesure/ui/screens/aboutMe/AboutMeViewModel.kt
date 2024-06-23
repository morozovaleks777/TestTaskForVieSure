package com.morozov.testtaskforviesure.ui.screens.aboutMe

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
data class AboutMeUiState(
    val bookDetailState: LoadableUiState<List<Book>> = LoadableUiState.Loading(),

    )

sealed class AboutMeAction {
    data class ShowToast(val message: String) : AboutMeAction()
    data object GoBack : AboutMeAction()

}

@HiltViewModel
class AboutMeViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AboutMeUiState())
    val uiState: StateFlow<AboutMeUiState> = _uiState.asStateFlow()

    val send: (AboutMeAction) -> Unit = { action ->
        when (action) {
            is AboutMeAction.GoBack -> {
                navigationManager.goBack()
            }

            else -> {
                throw IllegalArgumentException("Data for insticator WebView should be available")
            }

        }

    }

}