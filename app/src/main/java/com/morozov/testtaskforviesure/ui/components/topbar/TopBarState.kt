package com.morozov.testtaskforviesure.ui.components.topbar

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class TopBarState(
    val showAppBar: Boolean = true,
    val showBackIcon: Boolean = false,
    val customBackView: (@Composable () -> Unit)? = null,
    val backIconListener: () -> Unit = {},
    val showTitle: Boolean = true,
    val customTitleView: (@Composable () -> Unit)? = null,
    val actions: List<TopBarAction> = emptyList(),
    val overflowMenuItems: List<OverflowMenuAction> = emptyList(),
    val isTransparent: Boolean = false,
    val isCenterAligned: Boolean = false,
    val scrollStateProvider: (() -> LazyListState)? = null,
    val isDynamicContainerColor: Boolean = false,
    val dynamicContainerColor: (@Composable () -> Color)? = null
)


data class TopBarAction(
    val icon: ImageVector,
    val onClick: () -> Unit,
    val contentDescription: String
)

data class OverflowMenuAction(val title: String, val onClick: () -> Unit)