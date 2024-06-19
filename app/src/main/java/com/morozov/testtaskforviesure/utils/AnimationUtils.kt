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

fun getDeviceHeightButtonVisibility(
    scrollStateProvider: () -> LazyListState,
    deviceHeight: Int
): Boolean {
    val firstItemHeightPx: () -> Float = {
        val items = scrollStateProvider().layoutInfo.visibleItemsInfo
        if (items.isNotEmpty()) items[0].size.toFloat() else 1.0f
    }
    val currentOffsetPx: () -> Float = {
        if (scrollStateProvider().firstVisibleItemIndex > 0) firstItemHeightPx() else
            scrollStateProvider().firstVisibleItemScrollOffset.toFloat()
    }

    return deviceHeight - currentOffsetPx() <= 0
}


var previousOffset = 0
var scrollDirectionState = ScrollDirection.DOWN
fun getIsScrollBack(
    scrollState: () -> LazyListState,
): Boolean {
    val currentOffset = scrollState().firstVisibleItemScrollOffset

    if (currentOffset > previousOffset) {
        scrollDirectionState = ScrollDirection.DOWN
        previousOffset = currentOffset
    } else if (currentOffset < previousOffset) {
        scrollDirectionState = ScrollDirection.UP
        previousOffset = currentOffset
    }
    return scrollDirectionState == ScrollDirection.UP
}

enum class ScrollDirection {
    UP, DOWN
}

fun getIsPopupVisibleAtTheEndScroll(scrollStateProvider: () -> LazyListState): Boolean {

    val lastVisibleIndex =
        if (scrollStateProvider().firstVisibleItemIndex > 0)
            scrollStateProvider().layoutInfo.visibleItemsInfo.lastIndex + scrollStateProvider().firstVisibleItemIndex
        else scrollStateProvider().firstVisibleItemIndex

    val lastIndex = scrollStateProvider().layoutInfo.totalItemsCount - 1

    return lastIndex == lastVisibleIndex
}