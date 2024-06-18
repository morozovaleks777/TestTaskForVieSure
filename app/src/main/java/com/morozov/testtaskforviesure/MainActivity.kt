package com.morozov.testtaskforviesure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.morozov.testtaskforviesure.ui.BookApp
import com.morozov.testtaskforviesure.ui.theme.TestTaskForVieSureTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           TestTaskForVieSureTheme  {
               BookApp()
            }
        }
    }
}

