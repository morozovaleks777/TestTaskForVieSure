package com.morozov.testtaskforviesure.ui.screens.bookDetailScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.morozov.testtaskforviesure.navigation.BookDetail

@Composable
fun BookDetailScreen(
    args: BookDetail
){
    Text(args.toString())
}