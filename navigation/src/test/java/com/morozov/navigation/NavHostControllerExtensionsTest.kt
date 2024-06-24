package com.morozov.navigation

import androidx.navigation.NavHostController
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.eq
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
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
