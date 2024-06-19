package com.morozov.testtaskforviesure.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.morozov.testtaskforviesure.ui.theme.AppDimens
import com.morozov.testtaskforviesure.ui.theme.AppTypography
import com.morozov.testtaskforviesure.ui.theme.TextDefaultBase


@Composable
fun ErrorLayout(
    modifier: Modifier,
    parentTopPadding: Dp = 0.dp,
    errorMessage: String?,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = parentTopPadding)
            .padding(horizontal = AppDimens.screenSidePadding)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Error:",
            style = AppTypography.h3,
            color = TextDefaultBase
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = errorMessage.toString(),
            style = AppTypography.h5,
            color = TextDefaultBase
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Pull to retry.",
            style = AppTypography.h3,
            color = TextDefaultBase
        )
    }
}