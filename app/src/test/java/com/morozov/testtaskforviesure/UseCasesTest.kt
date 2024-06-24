import com.morozov.common.models.BookDomainEntity
import com.morozov.domain.domain.RoomRepository
import com.morozov.domain.domain.models.Book
import com.morozov.domain.domain.roomUseCases.DeleteBookByIdUseCase
import com.morozov.domain.domain.roomUseCases.DeleteBookUseCase
import com.morozov.domain.domain.roomUseCases.GetBookByIdUseCase
import com.morozov.domain.domain.roomUseCases.GetBooksUseCaseFromRoom
import com.morozov.domain.domain.roomUseCases.InsertBookUseCase
import com.morozov.domain.domain.roomUseCases.UpdateBookUseCase
import com.morozov.domain.domain.toDomainEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class UseCasesTest {

    @Mock
    private lateinit var roomRepository: RoomRepository

    private lateinit var deleteBookByIdUseCase: DeleteBookByIdUseCase
    private lateinit var deleteBookUseCase: DeleteBookUseCase
    private lateinit var getBookByIdUseCase: GetBookByIdUseCase
    private lateinit var getBooksUseCaseFromRoom: GetBooksUseCaseFromRoom
    private lateinit var updateBookUseCase: UpdateBookUseCase
    private lateinit var insertBookUseCase: InsertBookUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        deleteBookByIdUseCase = DeleteBookByIdUseCase(roomRepository)
        deleteBookUseCase = DeleteBookUseCase(roomRepository)
        getBookByIdUseCase = GetBookByIdUseCase(roomRepository)
        getBooksUseCaseFromRoom = GetBooksUseCaseFromRoom(roomRepository)
        updateBookUseCase = UpdateBookUseCase(roomRepository)
        insertBookUseCase = InsertBookUseCase(roomRepository)
    }

    @Test
    fun `test deleteBookByIdUseCase`() = runBlocking {
        val bookId = 1

        // Call the use case
        deleteBookByIdUseCase.invoke(bookId)

        // Verify the repository method is called with correct parameters
        verify(roomRepository).deleteBookById(bookId)
    }

    @Test
    fun `test deleteBookUseCase`() = runBlocking {
        val book = Book(
            id = 1,
            title = "Book",
            author = "",
            description = "",
            image = "",
            releaseDate = "",
            titlee = ""
        )

        // Call the use case
        deleteBookUseCase.invoke(book)

        // Verify the repository method is called with correct parameters
        verify(roomRepository).deleteBook(book.toDomainEntity())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test getBookByIdUseCase`() = runBlocking {
        val bookId = 1
        val mockBook = Book(
            id = bookId,
            title = "Book",
            author = "",
            description = "",
            image = "",
            releaseDate = "",
            titlee = ""
        )
        val mockEntity = mockBook.toDomainEntity()
        val mockFlow: Flow<BookDomainEntity?> = flow { emit(mockEntity) }

        `when`(roomRepository.getBookById(bookId)).thenReturn(mockFlow)

        // Call the use case
        val result = getBookByIdUseCase.invoke(bookId)

        // Collect the flow and verify the result
        result.collect { book ->
            val book1 = book?.copy(key = "")
            val mockBook1 = mockBook.copy(key = "")
            assertEquals(mockBook1, book1)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test getBooksUseCase`() = runBlocking {
        val mockBooks = listOf(
            Book(
                id = 1,
                title = "Book1",
                author = "",
                description = "",
                image = "",
                releaseDate = "",
                titlee = "",
                key = ""
            ),
            Book(
                id = 2,
                title = "Book2",
                author = "",
                description = "",
                image = "",
                releaseDate = "",
                titlee = "",
                key = ""
            )
        )
        val mockEntities = mockBooks.map { it.toDomainEntity() }
        val mockFlow: Flow<List<BookDomainEntity>> = flow { emit(mockEntities) }

        `when`(roomRepository.getAllBooks()).thenReturn(mockFlow)

        // Call the use case
        val result = getBooksUseCaseFromRoom.invoke()

        // Collect the flow and verify the result
        val books1 = result.map { book ->
            book.copy(key = "")
        }
        assertEquals(mockBooks, books1)
    }


    @Test
    fun `test updateBookUseCase`() = runBlocking {
        val book = Book(
            id = 1,
            title = "Book",
            author = "",
            description = "",
            image = "",
            releaseDate = "",
            titlee = ""
        )

        // Call the use case
        updateBookUseCase.invoke(book)

        // Verify the repository method is called with correct parameters
        verify(roomRepository).updateBook(book.toDomainEntity())
    }

    @Test
    fun `test insertBookUseCase`() = runBlocking {
        val book = Book(
            id = 1,
            title = "Book",
            author = "",
            description = "",
            image = "",
            releaseDate = "",
            titlee = ""
        )

        // Call the use case
        insertBookUseCase.invoke(book)

        // Verify the repository method is called with correct parameters
        verify(roomRepository).insertBook(book.toDomainEntity())
    }
}
