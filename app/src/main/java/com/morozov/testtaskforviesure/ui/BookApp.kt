package com.morozov.testtaskforviesure.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.morozov.navigation.AboutMe
import com.morozov.navigation.BookDetail
import com.morozov.navigation.Books
import com.morozov.navigation.Splash
import com.morozov.testtaskforviesure.ui.components.topbar.TopBarState
import com.morozov.navigation.onNavigationEvent
import com.morozov.testtaskforviesure.ui.components.topbar.BookAppBar
import com.morozov.testtaskforviesure.ui.screens.aboutMe.AboutMe
import com.morozov.testtaskforviesure.ui.screens.bookDetail.BookDetailScreen
import com.morozov.testtaskforviesure.ui.screens.bookDetail.BookDetailViewModel
import com.morozov.testtaskforviesure.ui.screens.booksScreen.BooksScreen
import com.morozov.testtaskforviesure.ui.screens.booksScreen.BooksViewModel
import com.morozov.testtaskforviesure.ui.screens.splash.SplashScreen
import com.morozov.testtaskforviesure.ui.screens.splash.SplashViewModel
import com.morozov.testtaskforviesure.utils.composablePage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flowOn

val LocalTopBarUpdater = compositionLocalOf<(TopBarState) -> Unit> {
    error("TopBar updater is null")
}


@Composable
fun BookApp(
    viewModel: BookAppViewModel,
) {
    val navController = rememberNavController()
    var topBarState by remember { mutableStateOf(TopBarState()) }
    val isNeedFullScreen = rememberSaveable {
        mutableStateOf(false)
    }
    UpdateSystemUIVisibilityStates(isNeedFullScreen)

    BookAppEventEffect(
        navController = navController,
        appEvents = viewModel.events.filterIsInstance()
    )

    CompositionLocalProvider(
        LocalTopBarUpdater provides { topBarState = it },

        ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { BookAppBar(topBarState) }

        ) { paddingValues ->

            NavHostGraph(paddingValues, navController)
        }
    }
}

@Composable
private fun NavHostGraph(
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
        navController = navController,
        startDestination = Splash
    ) {
        composablePage<Splash> {
            SplashScreen(
                modifier = Modifier,
                viewModel = hiltViewModel<SplashViewModel>(),
                navController = navController
            )
        }
        composablePage<Books> {

            BooksScreen(
                viewModel = hiltViewModel<BooksViewModel>(),
                parentTopPadding = paddingValues.calculateTopPadding()

            )

        }
        composablePage<BookDetail> {
            val args = it.toRoute<BookDetail>()
            BookDetailScreen(
                viewModel = hiltViewModel<BookDetailViewModel>(),
                args = args
            )
        }
        composablePage<AboutMe> {
            AboutMe(
            )
        }
    }
}

@Composable
private fun BookAppEventEffect(
    navController: NavHostController,
    appEvents: Flow<WDAppEvent>,

    ) {
    LaunchedEffect(Unit) {
        appEvents
            .flowOn(Dispatchers.Main)
            .collect { event ->
                when (event) {

                    is WDAppEvent.Navigation -> {
                        navController.onNavigationEvent(event.action)
                    }

                }
            }
    }
}

@SuppressLint("SourceLockedOrientationActivity")
@Composable
private fun UpdateSystemUIVisibilityStates(
    isPlayerFullScreen: MutableState<Boolean>,
) {
    val context = LocalContext.current

    (context as Activity?)?.run {
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        // Configure the behavior of the hidden system bars.
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        if (isPlayerFullScreen.value) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
            windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
        } else {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

            windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
            windowInsetsController.show(WindowInsetsCompat.Type.navigationBars())
            windowInsetsController.show(WindowInsetsCompat.Type.statusBars())
        }
    }
}