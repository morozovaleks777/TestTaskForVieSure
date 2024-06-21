package com.morozov.testtaskforviesure


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.LazyColumn
import androidx.hilt.navigation.compose.hiltViewModel
import com.morozov.testtaskforviesure.ui.BookApp
import com.morozov.testtaskforviesure.ui.BookAppViewModel
import com.morozov.testtaskforviesure.ui.theme.BookAppTheme

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookAppTheme {
                BookApp(viewModel = hiltViewModel<BookAppViewModel>())

            }
        }
    }
}

