package com.morozov.testtaskforviesure.ui.screens.booksScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.morozov.testtaskforviesure.ui.components.topbar.TopBarAction
import com.morozov.testtaskforviesure.ui.components.topbar.TopBarState
import com.morozov.testtaskforviesure.data.LoadableUiState
import com.morozov.testtaskforviesure.domain.Book
import com.morozov.testtaskforviesure.ui.LocalTopBarUpdater

@Composable
fun BooksScreen(
    viewModel: BooksViewModel,
    navController: NavHostController,
) {
    val topBarStateUpdater = LocalTopBarUpdater.current
    val topBarActions = prepareTopBarActions(sendAction = viewModel.send)
    LaunchedEffect(Unit) {
        topBarStateUpdater(
            TopBarState(
                actions = topBarActions,
                isTransparent = true,
            )
        )
    }

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
                                viewModel.send(BooksAction.GoToBookDetail(book))

                            })
                    }
                }
            }
        }

        is LoadableUiState.Error -> {}
        is LoadableUiState.Loading -> {}
    }
}

@Composable
private fun prepareTopBarActions(
    sendAction: (BooksAction) -> Unit,
): List<TopBarAction> {
    return listOf(
        TopBarAction(
            icon = Icons.Default.Person,
            onClick = { sendAction(BooksAction.AboutMe) },
            contentDescription = "app bar button mute"
        ),

        )
}