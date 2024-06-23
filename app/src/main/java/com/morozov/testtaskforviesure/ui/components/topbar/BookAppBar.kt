package com.morozov.testtaskforviesure.ui.components.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.morozov.testtaskforviesure.R
import com.morozov.testtaskforviesure.utils.scrollAnimatedColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookAppBar(
    topBarState: TopBarState,
) {
    with(topBarState) {

        if (showAppBar) {
            val containerColor: Color = when {
                isTransparent -> Color.Transparent
                isDynamicContainerColor -> {
                    topBarState.dynamicContainerColor?.invoke() ?: Color.Transparent
                }

                else -> MaterialTheme.colorScheme.background
            }
            val title: @Composable () -> Unit = {
                if (showTitle) {
                    customTitleView?.invoke() ?: DefaultTitle()
                }
            }
            val navigationIcon: @Composable () -> Unit = {
                if (showBackIcon) {
                    customBackView?.invoke() ?: DefaultNavigationBackIcon(backIconListener)
                }
            }
            val actions: @Composable RowScope.() -> Unit = {
                TopBarAction(items = actions)
                OverflowMenu(items = overflowMenuItems)
            }
            val colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(
                containerColor = containerColor
            )
            val modifier: Modifier = Modifier
                .then(scrollStateProvider?.invoke()?.let {
                    Modifier.drawBehind {
                        drawRect(scrollAnimatedColor(scrollStateProvider))
                    }
                } ?: Modifier)

            if (isCenterAligned) {
                CenterAlignedTopAppBar(
                    title = title,
                    navigationIcon = navigationIcon,
                    actions = actions,
                    colors = colors,
                    modifier = modifier
                )
            } else {
                TopAppBar(
                    title = title,
                    navigationIcon = navigationIcon,
                    actions = actions,
                    colors = colors,
                    modifier = modifier
                )
            }
        }
    }
}


@Composable
private fun DefaultTitle() {
    Icon(
        modifier = Modifier.width(80.dp).height(32.dp),
        painter = painterResource(id = R.drawable.logo_image),
        tint = Color.Unspecified,
        contentDescription = "logo"
    )
}

@Composable
private fun DefaultNavigationBackIcon(
    onBackClicked: () -> Unit
) {
    IconButton(
        onClick = {
            onBackClicked()
        }) {
        Icons.AutoMirrored.Default.ArrowBack
    }
}

@Composable
private fun TopBarAction(items: List<TopBarAction>) {
    items.forEach { action ->
        IconButton(onClick = { action.onClick() }) {
            Icon(
                imageVector = action.icon,
                tint = Color.White,
                contentDescription = action.contentDescription,
            )
        }
    }
}

@Composable
private fun OverflowMenu(items: List<OverflowMenuAction>) {
    if (items.isNotEmpty()) {

        var showMenu by remember { mutableStateOf(false) }

        //Overflow menu icon
        IconButton(onClick = { showMenu = true }) {
            Icons.Default.Menu
        }

        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
            items.forEach { item ->
                DropdownMenuItem(text = {
                    Text(text = item.title)
                }, onClick = {
                    showMenu = false
                    item.onClick()
                })
            }
        }
    }
}




