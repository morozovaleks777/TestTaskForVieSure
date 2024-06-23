package com.morozov.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavHostController
import timber.log.Timber


fun NavHostController.onNavigationEvent(event: NavigationAction) {
    when (event) {
        is NavigationAction.GoTo -> {
            navigated(event.direction)
        }

        else -> popBackIfAvailable()
    }
}

@SuppressLint("RestrictedApi")
fun NavHostController.navigated(direction: NavigationObject) {
    try {
        Timber.d(direction.toString())
        navigate(direction)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

fun NavHostController.popBackIfAvailable() {
    if (previousBackStackEntry != null) popBackStack()
}