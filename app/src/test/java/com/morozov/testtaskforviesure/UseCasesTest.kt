import com.morozov.testtaskforviesure.data.room.BookEntity
import com.morozov.testtaskforviesure.data.room.toEntity
import com.morozov.testtaskforviesure.domain.Book
import com.morozov.testtaskforviesure.domain.RoomRepository
import com.morozov.testtaskforviesure.domain.roomUseCases.DeleteBookByIdUseCase
import com.morozov.testtaskforviesure.domain.roomUseCases.DeleteBookUseCase
import com.morozov.testtaskforviesure.domain.roomUseCases.GetBookByIdUseCase
import com.morozov.testtaskforviesure.domain.roomUseCases.GetBooksUseCaseFromRoom
import com.morozov.testtaskforviesure.domain.roomUseCases.InsertBookUseCase
import com.morozov.testtaskforviesure.domain.roomUseCases.UpdateBookUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
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
        val book = Book(id = 1, title = "Book", author = "", description = "", image = "", releaseDate = "", titlee = "")

        // Call the use case
        deleteBookUseCase.invoke(book)

        // Verify the repository method is called with correct parameters
        verify(roomRepository).deleteBook(book.toEntity())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test getBookByIdUseCase`() = runBlocking {
        val bookId = 1
        val mockBook = Book(id = bookId, title = "Book", author = "", description = "", image = "", releaseDate = "", titlee = "")
        val mockEntity = mockBook.toEntity()
        val mockFlow: Flow<BookEntity?> = flow { emit(mockEntity) }

        `when`(roomRepository.getBookById(bookId)).thenReturn(mockFlow)

        // Call the use case
        val result = getBookByIdUseCase.invoke(bookId)

        // Collect the flow and verify the result
        result.collect { book ->
            assertEquals(mockBook, book)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test getBooksUseCase`() = runBlocking {
        val mockBooks = listOf(
            Book(id = 1, title = "Book1", author = "", description = "", image = "", releaseDate = "", titlee = ""),
            Book(id = 2, title = "Book2", author = "", description = "", image = "", releaseDate = "", titlee = "")
        )
        val mockEntities = mockBooks.map { it.toEntity() }
        val mockFlow: Flow<List<BookEntity>> = flow { emit(mockEntities) }

        `when`(roomRepository.getAllBooks()).thenReturn(mockFlow)

        // Call the use case
        val result = getBooksUseCaseFromRoom.invoke()

        // Collect the flow and verify the result
        result.collect { books ->
            assertEquals(mockBooks, books)
        }
    }

    @Test
    fun `test updateBookUseCase`() = runBlocking {
        val book = Book(id = 1, title = "Book", author = "", description = "", image = "", releaseDate = "", titlee = "")

        // Call the use case
        updateBookUseCase.invoke(book)

        // Verify the repository method is called with correct parameters
        verify(roomRepository).updateBook(book.toEntity())
    }

    @Test
    fun `test insertBookUseCase`() = runBlocking {
        val book = Book(id = 1, title = "Book", author = "", description = "", image = "", releaseDate = "", titlee = "")

        // Call the use case
        insertBookUseCase.invoke(book)

        // Verify the repository method is called with correct parameters
        verify(roomRepository).insertBook(book.toEntity())
    }
}
