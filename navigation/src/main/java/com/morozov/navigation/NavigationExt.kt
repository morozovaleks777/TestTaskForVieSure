package com.morozov.navigation

import androidx.navigation.NavHostController


fun NavHostController.onNavigationEvent(event: NavigationAction) {
    when (event) {
        is NavigationAction.GoTo -> {
            navigate(event.direction) {
                popUpTo(graph.startDestinationId) {
                    inclusive = true
                }
            }
        }

        else -> popBackIfAvailable()
    }
}

fun NavHostController.popBackIfAvailable() {
    if (previousBackStackEntry != null) popBackStack()
}