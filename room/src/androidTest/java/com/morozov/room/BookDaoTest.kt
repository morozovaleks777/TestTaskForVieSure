package com.morozov.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.morozov.room.room.BookDao
import com.morozov.room.room.BookDatabase
import com.morozov.room.room.BookEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain


@RunWith(AndroidJUnit4::class)
class BookDaoTest2 {


    private lateinit var database: BookDatabase
    private lateinit var bookDao: BookDao

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java).build()
        bookDao = database.bookDao()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        database.close()
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testInsertAndRetrieveBook() = runTest {
        val book =  BookEntity(id = 1, title = "Test Book", author = "Test Author", description = "",
            image = "", releaseDate = "", titlee = ""
        )
        bookDao.insertBook(book)

        val books = bookDao.getAllBooks().first()
        assert(books.isNotEmpty())
        assert(books[0].title == "Test Book")
        assert(books[0].author == "Test Author")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDeleteBook() = runTest {
        val book =  BookEntity(id = 1, title = "Test Book", author = "", description = "",
            image = "", releaseDate = "", titlee = ""
        )
        bookDao.insertBook(book)
        bookDao.deleteBook(book)

        val books = bookDao.getAllBooks().first()
        assert(books.isEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testUpdateBook() = runTest {
        val book =  BookEntity(id = 1, title = "Test Book", author = "", description = "",
            image = "", releaseDate = "", titlee = ""
        )
        bookDao.insertBook(book)

        val updatedBook =  BookEntity(id = 1, title = "Updated Test Book", author = "Updated Test Author", description = "",
            image = "", releaseDate = "", titlee = ""
        )
        bookDao.updateBook(updatedBook)

        val books = bookDao.getAllBooks().first()
        assert(books.isNotEmpty())
        assert(books[0].title == "Updated Test Book")
        assert(books[0].author == "Updated Test Author")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDeleteBookById() = runTest {
        val book =  BookEntity(id = 1, title = "Test Book", author = "", description = "",
            image = "", releaseDate = "", titlee = ""
        )
        bookDao.insertBook(book)
        bookDao.deleteBookById(book.id)

        val books = bookDao.getAllBooks().first()
        assert(books.isEmpty())
    }
}
