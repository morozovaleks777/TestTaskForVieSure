package com.morozov.testtaskforviesure.ui.screens.booksScreen

import android.util.Log
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.morozov.testtaskforviesure.R
import com.morozov.testtaskforviesure.data.LoadableUiState
import com.morozov.testtaskforviesure.domain.Book
import com.morozov.testtaskforviesure.ui.LocalTopBarUpdater
import com.morozov.testtaskforviesure.ui.components.ErrorLayout
import com.morozov.testtaskforviesure.ui.components.LoadingComponent
import com.morozov.testtaskforviesure.ui.components.topbar.TopBarAction
import com.morozov.testtaskforviesure.ui.components.topbar.TopBarState
import com.morozov.testtaskforviesure.ui.theme.AppTypography
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(
    viewModel: BooksViewModel,
    parentTopPadding: Dp,
) {
    val topBarStateUpdater = LocalTopBarUpdater.current
    val topBarActions = prepareTopBarActions(sendAction = viewModel.send)
    val uiState by viewModel.uiState.collectAsState()

    val refreshing = uiState.isRefreshing
    val state = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = {
            viewModel.send(BooksAction.GetBooksData(isRefreshing = true))
        })
    val lazyListState = rememberLazyListState()

    LaunchedEffect(Unit) {
        topBarStateUpdater(
            TopBarState(
                actions = topBarActions,
                isTransparent = true,
            )
        )
    }

    LaunchedEffect(Unit) {
        if (uiState.booksPageState !is LoadableUiState.Available) {
            Log.d("invoked", "BooksScreen: ")
            viewModel.send(BooksAction.GetBooksData())
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .pullRefresh(state)) {
        when (uiState.booksPageState) {
            is LoadableUiState.Available -> {
                val books = remember{(uiState.booksPageState as LoadableUiState.Available<List<Book>>).data}
                BookList(
                    books = books,
                    lazyListState = lazyListState) { book ->
                    viewModel.send(BooksAction.GoToBookDetail(book))
                }
                PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
            }

            is LoadableUiState.Error -> {

                ErrorLayout(
                    modifier = Modifier,
                    parentTopPadding = parentTopPadding,
                    errorMessage = (uiState.booksPageState as LoadableUiState.Error<List<Book>>).message,
                )
                PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
            }

            is LoadableUiState.Loading -> {
                LoadingComponent(modifier = Modifier.fillMaxSize())
            }
        }
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

@Composable
fun BookList(books: List<Book>,lazyListState:LazyListState, onBookClick: (Book) -> Unit) {

    LazyColumn(
        state = lazyListState
    ){

        items(books+books + books + books) { book ->
            Text(book.title.orEmpty())
           // BookListItem(book = book, onBookClick = onBookClick)
        }
    }
//    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//        books.forEach{book ->
//        BookListItem(book = book, onBookClick = onBookClick)
//    }}

}

@Composable
fun BookListItem(book: Book, onBookClick: (Book) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onBookClick(book) },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp, pressedElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data =
                    book.image)
                        .apply<ImageRequest.Builder>(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.placeholder_image)
                            error(R.drawable.placeholder_image)
                            memoryCachePolicy(CachePolicy.ENABLED)
                        }).build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = book.title ?: "Unknown Title",
                    style = AppTypography.cta,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = book.description ?: "No description",
                    style = AppTypography.sub1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Composable
fun LazyListScope.Booky(books: List<Book>, onBookClick: (Book) -> Unit){
    books.forEach{book ->
        BookListItem(book = book, onBookClick = onBookClick)
    }
}