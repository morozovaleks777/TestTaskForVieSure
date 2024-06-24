package com.morozov.testtaskforviesure

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.morozov.domain.domain.GetBooksUseCase
import com.morozov.domain.domain.models.Book
import com.morozov.domain.domain.roomUseCases.GetBooksUseCaseFromRoom
import com.morozov.domain.domain.roomUseCases.InsertBookUseCase
import com.morozov.navigation.AboutMe
import com.morozov.navigation.BookDetail
import com.morozov.navigation.NavigationManager
import com.morozov.navigation.goToAboutMe
import com.morozov.navigation.goToBooksDetail
import com.morozov.testtaskforviesure.ui.screens.booksScreen.BooksAction
import com.morozov.testtaskforviesure.ui.screens.booksScreen.BooksViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class BooksViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Mocked dependencies
    @Mock
    private lateinit var navigationManager: NavigationManager

    @Mock
    private lateinit var getBooksUseCase: GetBooksUseCase

    @Mock
    private lateinit var insertBookUseCase: InsertBookUseCase

    @Mock
    private lateinit var getBooksUseCaseFromRoom: GetBooksUseCaseFromRoom

    // ViewModel to be tested
    private lateinit var viewModel: BooksViewModel

    // TestCoroutineDispatcher for testing coroutines
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        // Provide the mocked dependencies to the ViewModel
        viewModel = BooksViewModel(
            navigationManager,
            getBooksUseCase,
            insertBookUseCase,
            getBooksUseCaseFromRoom
        )

        // Set the test dispatcher as the main dispatcher
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // Reset main dispatcher after the test
        Dispatchers.resetMain()
        testDispatcher.scheduler
    }



    @Test
    fun testGoToBookDetail() {
        // Mock book data
        val mockBook = Book("1", "Book 1", 1, "Description 1", "2023-01-01", "","")

        // Call the send function with GoToBookDetail action
        viewModel.send(BooksAction.GoToBookDetail(mockBook))

        // Verify navigationManager.goToBooksDetail() was called with correct BookDetail object
        Mockito.verify(navigationManager, Mockito.times(1)).goToBooksDetail(
            BookDetail(
                id = mockBook.id,
                title = mockBook.title,
                author = mockBook.author,
                description = mockBook.description,
                image = mockBook.image,
                releaseDate = mockBook.releaseDate
            )
        )
    }

    @Test
    fun testAboutMeNavigation() {
        // Call the send function with AboutMe action
        viewModel.send(BooksAction.AboutMe)

        // Verify navigationManager.goToAboutMe() was called with AboutMe object
        Mockito.verify(navigationManager, Mockito.times(1)).goToAboutMe(AboutMe)
    }
}
