package com.morozov.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationAction {
    @Serializable
    data class GoTo(val direction: NavigationObject) : NavigationAction()
    data object GoBack : NavigationAction()
}
