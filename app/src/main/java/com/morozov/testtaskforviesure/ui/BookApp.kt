package com.morozov.testtaskforviesure.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.morozov.testtaskforviesure.navigation.BookDetail
import com.morozov.testtaskforviesure.navigation.Books
import com.morozov.testtaskforviesure.navigation.Splash
import com.morozov.testtaskforviesure.navigation.onNavigationEvent
import com.morozov.testtaskforviesure.ui.screens.bookDetailScreen.BookDetailScreen
import com.morozov.testtaskforviesure.ui.screens.booksScreen.BooksScreen
import com.morozov.testtaskforviesure.ui.screens.booksScreen.BooksViewModel
import com.morozov.testtaskforviesure.ui.screens.splash.SplashScreen
import com.morozov.testtaskforviesure.ui.screens.splash.SplashViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flowOn


@Composable
 fun BookApp(
     viewModel: BookAppViewModel,
 ) {
    val navController = rememberNavController()

  WDAppEventEffect(navController =navController,
      appEvents =  viewModel.events.filterIsInstance())

    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(
                modifier = Modifier,
                viewModel = SplashViewModel(),
                navController = navController
            )
        }
        composable<Books> {

            BooksScreen(
                navController = navController,
                viewModel = hiltViewModel<BooksViewModel>(),

            )

        }
        composable<BookDetail> {
            val args = it.toRoute<BookDetail>()
            BookDetailScreen(
                args = args
                )

        }
    }
}

@Composable
private fun WDAppEventEffect(
    navController: NavHostController,
    appEvents: Flow<WDAppEvent>,

) {
    LaunchedEffect(Unit) {
        appEvents
            .flowOn(Dispatchers.Main)
            .collect { event ->
                when (event) {

                    is WDAppEvent.Navigation ->{
                        navController.onNavigationEvent(event.action)}

                }
            }
    }
}