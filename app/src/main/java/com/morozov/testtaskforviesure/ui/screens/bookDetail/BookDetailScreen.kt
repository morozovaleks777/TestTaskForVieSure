package com.morozov.testtaskforviesure.ui.screens.bookDetail


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.morozov.navigation.BookDetail
import com.morozov.testtaskforviesure.R.drawable
import com.morozov.testtaskforviesure.ui.LocalTopBarUpdater
import com.morozov.testtaskforviesure.ui.components.topbar.TopBarAction
import com.morozov.testtaskforviesure.ui.components.topbar.TopBarState
import com.morozov.testtaskforviesure.ui.theme.AppTypography
import com.morozov.testtaskforviesure.ui.theme.Grey
import com.morozov.testtaskforviesure.utils.clickableNoRipple


@Composable
fun BookDetailScreen(
    args: BookDetail,
    viewModel: BookDetailViewModel
) {
    val topBarStateUpdater = LocalTopBarUpdater.current
    val topBarActions = prepareTopBarActions(sendAction = viewModel.send)
    LaunchedEffect(Unit) {
        topBarStateUpdater(
            TopBarState(
                customBackView = {
                    Icon(
                        modifier = Modifier
                            .clickableNoRipple { viewModel.send(BookDetailAction.GoBack) },
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Grey // Set the desired color here
                    )
                },
                isCenterAligned = true,
                showTitle = true,
                customTitleView = { Text(args.title) },
                showBackIcon = true,
                actions = topBarActions,
                isTransparent = true,
            )
        )
    }
    BookDetailLayout(book = args)
}


@Composable
private fun prepareTopBarActions(
    sendAction: (BookDetailAction) -> Unit,
): List<TopBarAction> {
    return listOf(
//        TopBarAction(
//            icon = Icons.AutoMirrored.Default.ArrowBack,
//            onClick = { sendAction(BookDetailAction.GoBack) },
//            contentDescription = "app bar button mute"
//        ),

    )
}


@Composable
fun BookDetailLayout(book: BookDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val releaseDate =
            book.releaseDate.let { if (it.isEmpty()) "unknown date" else book.releaseDate }
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = book.image)
                    .apply(block = fun ImageRequest.Builder.() {
                        placeholder(drawable.placeholder_image)
                        error(drawable.placeholder_image)
                    }).build()
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = book.title,
            style = AppTypography.h5
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            text = releaseDate,
            style = AppTypography.sub2,
            color = Grey
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            text = book.description,
            style = AppTypography.sub1
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.navigationBarsPadding(),
            text = "Author: ${book.author}",
            style = AppTypography.sub2,
            color = Grey
        )
    }
}
