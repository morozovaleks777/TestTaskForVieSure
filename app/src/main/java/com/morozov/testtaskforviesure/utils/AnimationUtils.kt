package com.morozov.testtaskforviesure.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.graphics.Color
import com.morozov.testtaskforviesure.ui.theme.BlackMain
import kotlin.math.min

fun scrollAnimatedColor(
    scrollStateProvider: () -> LazyListState,
    color: Color = BlackMain
): Color {
    return color.copy(
        alpha = getFirstItemScrollFraction(scrollStateProvider)
    )
}

fun getFirstItemScrollFraction(
    scrollStateProvider: () -> LazyListState,
): Float {
    val firstItemHeightPx: () -> Float = {
        val items = scrollStateProvider().layoutInfo.visibleItemsInfo
        if (items.isNotEmpty()) items[0].size.toFloat() else 1.0f
    }
    val currentOffsetPx: () -> Float = {
        if (scrollStateProvider().firstVisibleItemIndex > 0) firstItemHeightPx() else
            scrollStateProvider().firstVisibleItemScrollOffset.toFloat()
    }
    val scrollFraction: () -> Float = {
        min(1f, currentOffsetPx() / firstItemHeightPx())
    }
    return scrollFraction()
}

