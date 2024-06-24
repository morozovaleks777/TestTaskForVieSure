package com.morozov.testtaskforviesure

import com.morozov.navigation.NavigationManager
import com.morozov.navigation.goBack
import com.morozov.testtaskforviesure.ui.screens.aboutMe.AboutMeAction
import com.morozov.testtaskforviesure.ui.screens.aboutMe.AboutMeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class AboutMeViewModelTest {

    private lateinit var viewModel: AboutMeViewModel

    // Mocked dependencies
    @Mock
    private lateinit var navigationManager: NavigationManager

    // TestCoroutineDispatcher for testing coroutines
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        // Provide the mocked NavigationManager to the ViewModel
        viewModel = AboutMeViewModel(navigationManager)

        // Set the test dispatcher as the main dispatcher
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // Reset main dispatcher after the test
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testNavigateBack() {
        // Call the send function with GoBack action
        viewModel.send(AboutMeAction.GoBack)

        // Verify that navigationManager.goBack() was called exactly once
        Mockito.verify(navigationManager, Mockito.times(1)).goBack()
    }
}
