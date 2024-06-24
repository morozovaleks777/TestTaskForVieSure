package com.morozov.navigation

import androidx.navigation.NavHostController
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NavHostControllerExtensionsTest {

    @Mock
    private lateinit var navController: NavHostController

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testOnNavigationEventGoBack() {
        navController.onNavigationEvent(NavigationAction.GoBack)
        verify(navController).popBackIfAvailable()
    }
}
