package com.morozov.testtaskforviesure.ui.screens.splash

import androidx.lifecycle.ViewModel
import com.morozov.navigation.Books
import com.morozov.navigation.NavigationManager
import com.morozov.navigation.goToMain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


sealed class SplashAction {
    data object GoToMain : SplashAction()
}

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
) : ViewModel() {

    val send: (SplashAction) -> Unit = { action ->
        when (action) {
            is SplashAction.GoToMain -> {
                navigationManager.goToMain(Books)
            }
        }
    }
}