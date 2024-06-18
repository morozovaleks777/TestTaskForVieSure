package com.morozov.testtaskforviesure.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.morozov.testtaskforviesure.navigation.Books
import com.morozov.testtaskforviesure.navigation.Splash
import com.morozov.testtaskforviesure.ui.screens.booksScreen.BooksScreen
import com.morozov.testtaskforviesure.ui.screens.booksScreen.BooksViewModel
import com.morozov.testtaskforviesure.ui.screens.splash.SplashScreen
import com.morozov.testtaskforviesure.ui.screens.splash.SplashViewModel


@Composable
 fun BookApp() {
    val navController = rememberNavController()
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
            val args = it.toRoute<Books>()
            BooksScreen(
                navController = navController,
                viewModel = hiltViewModel<BooksViewModel>()
            )

        }
    }
}