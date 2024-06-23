package com.morozov.testtaskforviesure.ui.components.topbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.morozov.testtaskforviesure.ui.theme.AppTypography
import com.morozov.testtaskforviesure.ui.theme.TextDefaultBase


@Composable
fun TopBarTitle(
    modifier: Modifier,
    text: String,
    textColor: Color = TextDefaultBase,
    topBarBackButtonVisible: Boolean = true,
    topBarActionCount: Int = 0
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = (40 * topBarActionCount).dp, //40 = width of action icon
                end = if (topBarBackButtonVisible) 48.dp else 0.dp //48 = width of the back button
            ),
        text = text,
        style = AppTypography.sub2,
        color = textColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ShortBackArrowIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icons.AutoMirrored.Filled.ArrowBack
    }
}

@Composable
fun CloseIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = Modifier,
        onClick = onClick
    ) {
        Icons.Default.Close
    }
}