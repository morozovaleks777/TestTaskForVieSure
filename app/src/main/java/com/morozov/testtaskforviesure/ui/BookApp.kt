package com.morozov.testtaskforviesure.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.morozov.testtaskforviesure.navigation.Books
import com.morozov.testtaskforviesure.navigation.Splash
import com.morozov.testtaskforviesure.ui.screens.booksScreen.BooksScreen
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
            BooksScreen()
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(text = "${args.name}, ${args.age} years old")
//            }
        }
    }
}