package com.morozov.domain

import com.morozov.common.models.BookDomainEntity
import com.morozov.domain.domain.RoomRepository
import com.morozov.domain.domain.models.Book
import com.morozov.domain.domain.roomUseCases.DeleteBookByIdUseCase
import com.morozov.domain.domain.roomUseCases.DeleteBookUseCase
import com.morozov.domain.domain.roomUseCases.GetBookByIdUseCase
import com.morozov.domain.domain.roomUseCases.GetBooksUseCaseFromRoom
import com.morozov.domain.domain.roomUseCases.InsertBookUseCase
import com.morozov.domain.domain.toDomainEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DeleteBookByIdUseCaseTest {

    // Mocked dependencies
    @Mock
    private lateinit var bookRepository: RoomRepository

    // Class under test
    private lateinit var deleteBookByIdUseCase: DeleteBookByIdUseCase

    @Before
    fun setup() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this)

        // Create instance of DeleteBookByIdUseCase with mocked repository
        deleteBookByIdUseCase = DeleteBookByIdUseCase(bookRepository)
    }

    @Test
    fun `invoke should delete book by id`() = runBlocking {
        val bookId = 1

        // Call the use case
        deleteBookByIdUseCase(bookId)

        // Verify that deleteBookById method is called with correct id
        Mockito.verify(bookRepository).deleteBookById(bookId)
    }
}


class DeleteBookUseCaseTest {

    // Mocked dependencies
    @Mock
    private lateinit var bookRepository: RoomRepository

    // Class under test
    private lateinit var deleteBookUseCase: DeleteBookUseCase

    @Before
    fun setup() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this)

        // Create instance of DeleteBookUseCase with mocked repository
        deleteBookUseCase = DeleteBookUseCase(bookRepository)
    }

    @Test
    fun `invoke should delete book`() = runBlocking {
        val book =
            Book("Sample Author", "Sample Book", 1, "Sample Description", "", "2024-06-25", "")

        // Call the use case
        deleteBookUseCase(book)

        // Verify that deleteBook method is called with correct book entity
        Mockito.verify(bookRepository).deleteBook(book.toDomainEntity())
    }
}


class GetBookByIdUseCaseTest {

    // Mocked dependencies
    @Mock
    private lateinit var bookRepository: RoomRepository

    // Class under test
    private lateinit var getBookByIdUseCase: GetBookByIdUseCase

    @Before
    fun setup() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this)

        // Create instance of GetBookByIdUseCase with mocked repository
        getBookByIdUseCase = GetBookByIdUseCase(bookRepository)
    }

    @Test
    fun `invoke should return book by id`() = runBlocking {
        val bookId = 1
        val expectedBook =
            BookDomainEntity(1, "Sample Book", "", "Sample Description", "", "2024-06-25", "")

        // Mock repository behavior
        `when`(bookRepository.getBookById(bookId)).thenReturn(flow {
            emit(expectedBook)
        })

        // Call the use case
        val result = getBookByIdUseCase(bookId).first()

        // Verify the result
        assertEquals(expectedBook, result?.toDomainEntity())
    }
}


class GetBooksUseCaseFromRoomTest {

    // Mocked dependencies
    @Mock
    private lateinit var bookRepository: RoomRepository

    // Class under test
    private lateinit var getBooksUseCaseFromRoom: GetBooksUseCaseFromRoom

    @Before
    fun setup() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this)

        // Create instance of GetBooksUseCaseFromRoom with mocked repository
        getBooksUseCaseFromRoom = GetBooksUseCaseFromRoom(bookRepository)
    }

    @Test
    fun `invoke should return list of books from room`() = runBlocking {
        val expectedBooks = listOf(
            Book(
                "Sample Author 1",
                "Sample Book 1",
                1,
                "Sample Description 1",
                "",
                "2024-06-25",
                "",
                ""
            ),
            Book(
                "Sample Author 2",
                "Sample Book 2",
                2,
                "Sample Description 2",
                "",
                "2024-06-26",
                "",
                ""
            )
        )

        // Mock repository behavior
        `when`(bookRepository.getAllBooks()).thenReturn(flow {
            emit(expectedBooks.map { it.toDomainEntity() })
        })

        // Call the use case
        val result = getBooksUseCaseFromRoom()
        result.map { it.key = "" }
        // Verify the result
        assertEquals(expectedBooks, result)
    }
}


class InsertBookUseCaseTest {

    // Mocked dependencies
    @Mock
    private lateinit var bookRepository: RoomRepository

    // Class under test
    private lateinit var insertBookUseCase: InsertBookUseCase

    @Before
    fun setup() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this)

        // Create instance of InsertBookUseCase with mocked repository
        insertBookUseCase = InsertBookUseCase(bookRepository)
    }

    @Test
    fun `invoke should insert book`() = runBlocking {
        val book =
            Book("Sample Author", "Sample Book", 1, "Sample Description", "", "2024-06-25", "")

        // Call the use case
        insertBookUseCase(book)

        // Verify that insertBook method is called with correct book entity
        Mockito.verify(bookRepository).insertBook(book.toDomainEntity())
    }
}
