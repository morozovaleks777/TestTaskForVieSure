package com.morozov.testtaskforviesure.ui.screens.booksScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.morozov.testtaskforviesure.data.LoadableUiState
import com.morozov.testtaskforviesure.domain.Book

@Composable
fun BooksScreen(
    viewModel: BooksViewModel,
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        viewModel.send(BooksAction.GetBooksData())
    }
    val uiState by viewModel.uiState.collectAsState()

    when (uiState.booksPageState) {
        is LoadableUiState.Available -> {
            val books = (uiState.booksPageState as LoadableUiState.Available<List<Book>>).data
            Column(modifier = Modifier.fillMaxSize()) {
                Text("Books List")
                LazyColumn {
                    items(books) { book ->
                        Text("Title: ${book.title}, Author: ${book.author}",
                            modifier = Modifier.clickable {

                            })
                    }
                }
            }
        }
        is LoadableUiState.Error -> {}
        is LoadableUiState.Loading -> {}
    }
}