package com.morozov.testtaskforviesure


import com.morozov.common.ApiError
import com.morozov.common.ApiResult
import com.morozov.domain.domain.models.Book
import com.morozov.domain.domain.GetBooksUseCase
import com.morozov.domain.domain.ApiRepository

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetBooksUseCaseFromRoomTest {

    @Mock
    private lateinit var repository: ApiRepository

    private lateinit var getBooksUseCase: GetBooksUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getBooksUseCase = GetBooksUseCase(repository)
    }

    @Test
    fun `test invoke returns success`() = runBlocking {
        // Mock data
        val mockBooks = listOf(
            Book(id = 1, title = "Book1", author = "", description = "", image = "", releaseDate = "", titlee = ""),
            Book(id = 2, title = "Book2", author = "", description = "", image = "", releaseDate = "", titlee = "")
        )
        val mockResult = ApiResult(success = true, data = mockBooks, error = null)

        // Mock repository response
        `when`(repository.getBooks()).thenReturn(mockResult)

        // Call the use case
        val result = getBooksUseCase.invoke()

        // Verify the result
        assertEquals(true, result.success)
        assertEquals(mockBooks, result.data)
        assertEquals(null, result.error)
    }

    @Test
    fun `test invoke returns error`() = runBlocking {
        // Mock error
        val mockError = ApiResult<List<Book>>(success = false, data = null, error = ApiError("An error occurred"))

        // Mock repository response
        `when`(repository.getBooks()).thenReturn(mockError)

        // Call the use case
        val result = getBooksUseCase.invoke()

        // Verify the result
        assertEquals(false, result.success)
        assertEquals(null, result.data)
        assertEquals("An error occurred", result.error?.message)
    }
}
