package com.morozov.testtaskforviesure.utils

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.morozov.testtaskforviesure.utils.AppAnimationEasing.STANDARD_ACCELERATE
import com.morozov.testtaskforviesure.utils.AppAnimationEasing.STANDARD_DECELERATE


inline fun <reified T : Any> NavGraphBuilder.composablePage(
    noinline enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = Transitions.PAGE_IN,
    noinline exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = Transitions.PAGE_OUT,
    crossinline content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
) {

    composable<T>(
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = enterTransition,
        popExitTransition = exitTransition,
    )
    { navBackStackEntry -> Box(Modifier.fillMaxSize()) { content(navBackStackEntry) } }

}


object Transitions {

    val PAGE_IN: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = {
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Start,
            animationSpec = tween(
                durationMillis = AnimationDuration.SLOW_01,
                easing = STANDARD_DECELERATE
            )
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = AnimationDuration.SLOW_01,
                easing = STANDARD_DECELERATE
            )
        )
    }
    val PAGE_OUT: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = {
        slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.End,
            animationSpec = tween(
                durationMillis = AnimationDuration.SLOW_02,
                easing = STANDARD_ACCELERATE
            )
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = AnimationDuration.SLOW_02,
                easing = STANDARD_ACCELERATE
            )
        )
    }
}


object AnimationDuration {
    const val FAST_01 = 100 // milliseconds (1 beat)
    const val MEDIUM_01 = 300 // milliseconds (3 beats)
    const val MEDIUM_02 = 400 // milliseconds (4 beats)
    const val SLOW_01 = 600 // milliseconds (6 beats)
    const val SLOW_02 = 700 // milliseconds (7 beats)
}

object AppAnimationEasing {
    val STANDARD = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f)
    val STANDARD_DECELERATE = CubicBezierEasing(0.25f, 1f, 0.35f, 1f)
    val STANDARD_ACCELERATE = CubicBezierEasing(0.75f, 0.05f, 0.85f, 0.05f)
    val LINEAR = CubicBezierEasing(0f, 0f, 1f, 1f)
}

