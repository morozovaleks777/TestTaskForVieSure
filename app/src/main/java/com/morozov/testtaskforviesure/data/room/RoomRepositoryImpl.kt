package com.morozov.testtaskforviesure.data.room


import com.morozov.testtaskforviesure.domain.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
) : RoomRepository {
    override fun getAllBooks(): Flow<List<BookEntity>> = bookDao.getAllBooks()

    override fun getBookById(id: Int): Flow<BookEntity?> = bookDao.getBookById(id)

    override suspend fun insertBooks(books: List<BookEntity>) = bookDao.insertBooks(books)

    override suspend fun insertBook(book: BookEntity) = bookDao.insertBook(book)

    override suspend fun updateBook(book: BookEntity) = bookDao.updateBook(book)

    override suspend fun deleteBook(book: BookEntity) = bookDao.deleteBook(book)

    override suspend fun deleteBookById(id: Int) = bookDao.deleteBookById(id)
}