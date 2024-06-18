package com.morozov.testtaskforviesure.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.navigation.NavHostController


fun NavHostController.onNavigationEvent(event: NavigationAction) {
    when (event) {
        is NavigationAction.GoTo ->   {
            Log.d("post", "onNavigationEvent: $event -> ${event.direction}")
            navigated(event.direction)}
        else -> popBackIfAvailable()
    }
}

@SuppressLint("RestrictedApi")
fun NavHostController.navigated(direction: NavigationObject) {
    try {
       // Timber.d(direction.toString())
        Log.d("post", "navigated: $direction")
            navigate(direction)
    } catch (e: Exception) {
       // Timber.e(e)
    }
}

fun NavHostController.popBackIfAvailable() {
    if (previousBackStackEntry != null) popBackStack()
}