package com.morozov.testtaskforviesure.ui.screens.aboutMe

import androidx.lifecycle.ViewModel
import com.morozov.navigation.NavigationManager
import com.morozov.navigation.goBack
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


sealed class AboutMeAction {
    data object GoBack : AboutMeAction()

}

@HiltViewModel
class AboutMeViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
) : ViewModel() {


    val send: (AboutMeAction) -> Unit = { action ->
        when (action) {
            is AboutMeAction.GoBack -> {
                navigationManager.goBack()
            }
        }
    }
}