package com.morozov.testtaskforviesure.ui.screens.splash

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel


@Stable
data class SplashUiState(
    val splashMessage: String = "DailyWire Plus",
)

sealed class SplashAction {
    data object GoToMain : SplashAction()
}


class SplashViewModel (

) :  ViewModel() {



}