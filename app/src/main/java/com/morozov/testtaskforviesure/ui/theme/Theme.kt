package com.morozov.testtaskforviesure.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = BlackMain,
    onPrimary = Color.White,
    secondary = PurpleGrey80,
    onSecondary = Color.White,
    tertiary = Grey7,
    onTertiary = Color.White,
    background = BlackMain,
    onBackground = Color.White,
    surface = Grey7,
    onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val LocalAppDimens = staticCompositionLocalOf { DefaultDimensions }
private val LocalAppTypography = staticCompositionLocalOf { DefaultTypography }

@Composable
fun ProvideDimens(
    dimensions: BookAppDimensions,
    content: @Composable () -> Unit
) {
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalAppDimens provides dimensionSet, content = content)
}

@Composable
fun ProvideTypography(
    appTypography: BookAppTypography,
    content: @Composable () -> Unit
) {
    val typography = remember { appTypography }
    CompositionLocalProvider(LocalAppTypography provides typography, content = content)

}

@Composable
fun BookAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    //No support for Light mode for now - design only in dark mode.
    /*
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
     */

    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) SmallDimensions else DefaultDimensions
    val typography = if (configuration.screenWidthDp <= 360) SmallTypography else DefaultTypography

    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = BlackMain.toArgb()

            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    ProvideDimens(dimensions = dimensions) {
        ProvideTypography(appTypography = typography) {
            MaterialTheme(
                colorScheme = colorScheme,
                typography = CompanionTypography,
                content = content
            )
        }
    }
}

object BookAppTheme {
    val dimens: BookAppDimensions
        @Composable
        get() = LocalAppDimens.current

    val typography: BookAppTypography
        @Composable
        get() = LocalAppTypography.current
}

val AppDimens: BookAppDimensions
    @Composable
    get() = BookAppTheme.dimens

val AppTypography: BookAppTypography
    @Composable
    get() = BookAppTheme.typography