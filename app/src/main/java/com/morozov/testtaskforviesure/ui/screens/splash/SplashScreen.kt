package com.morozov.testtaskforviesure.ui.screens.splash


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.morozov.navigation.Books
import com.morozov.navigation.Splash
import com.morozov.testtaskforviesure.ui.components.topbar.TopBarState
import com.morozov.testtaskforviesure.R
import com.morozov.testtaskforviesure.ui.LocalTopBarUpdater
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    modifier: Modifier,
    viewModel: SplashViewModel,
    navController: NavHostController,
) {


    val topBarStateUpdater = LocalTopBarUpdater.current


    LaunchedEffect(Unit) {
        topBarStateUpdater(TopBarState(showAppBar = false))
//         CoroutineScope(Dispatchers.Default).launch {
        //  Simulate loading data
        delay(800)
        navController.navigate(Books) {
            popUpTo(Splash) { inclusive = true }
        }
    }

    SplashLayout(
        modifier = modifier,
    )
}

@Composable
private fun SplashLayout(
    modifier: Modifier,

    ) {
    val configuration = LocalConfiguration.current
    val imageWidth = remember { (configuration.screenWidthDp * 0.53).dp }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.width(imageWidth),
            painter = painterResource(id = R.drawable.logo_image),
            contentScale = ContentScale.FillWidth,
            contentDescription = "logo image"
        )
    }
}

