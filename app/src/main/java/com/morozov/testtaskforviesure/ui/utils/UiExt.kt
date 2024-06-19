package com.morozov.testtaskforviesure.ui.utils

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.morozov.testtaskforviesure.ui.theme.LazyLoadingColor
import com.morozov.testtaskforviesure.ui.theme.ShimmerColor



fun Modifier.clickableNoRipple(onClick: () -> Unit): Modifier = composed {
    this.clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}


fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetY by transition.animateFloat(
        initialValue = -2 * size.height.toFloat(),
        targetValue = 2 * size.height.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200
            )
        ),
        label = "Shimmer Animation",
    )
    background(
        brush = Brush.verticalGradient(
            colors = listOf(
                LazyLoadingColor,
                ShimmerColor,
                LazyLoadingColor,
            ),
            startY = startOffsetY,
            endY = startOffsetY + size.height,
        )
    ).onGloballyPositioned {
        size = it.size
    }
}