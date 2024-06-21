package com.morozov.testtaskforviesure.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.morozov.testtaskforviesure.ui.components.topbar.TopBarState
import com.morozov.testtaskforviesure.navigation.AboutMe
import com.morozov.testtaskforviesure.navigation.BookDetail
import com.morozov.testtaskforviesure.navigation.Books
import com.morozov.testtaskforviesure.navigation.Splash
import com.morozov.testtaskforviesure.navigation.composablePage
import com.morozov.testtaskforviesure.navigation.onNavigationEvent
import com.morozov.testtaskforviesure.ui.components.topbar.BookAppBar
import com.morozov.testtaskforviesure.ui.screens.aboutMe.AboutMe
import com.morozov.testtaskforviesure.ui.screens.bookDetail.BookDetailScreen
import com.morozov.testtaskforviesure.ui.screens.bookDetail.BookDetailViewModel
import com.morozov.testtaskforviesure.ui.screens.booksScreen.BooksScreen
import com.morozov.testtaskforviesure.ui.screens.booksScreen.BooksViewModel
import com.morozov.testtaskforviesure.ui.screens.splash.SplashScreen
import com.morozov.testtaskforviesure.ui.screens.splash.SplashViewModel
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

    BookAppEventEffect(
        navController = navController,
        appEvents = viewModel.events.filterIsInstance()
    )

    CompositionLocalProvider(
        LocalTopBarUpdater provides { topBarState = it },

        ) {
        Scaffold(
            modifier = Modifier,
            topBar = { BookAppBar(topBarState) }

        ) { paddingValues ->

            NavHost(
                modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
                navController = navController,
                startDestination = Splash
            ) {
                composablePage<Splash> {
                    SplashScreen(
                        modifier = Modifier,
                        viewModel = SplashViewModel(),
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