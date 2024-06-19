package com.morozov.testtaskforviesure.domain

import com.morozov.testtaskforviesure.data.room.BookEntity
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    fun getAllBooks(): Flow<List<BookEntity>>
    fun getBookById(id: Int): Flow<BookEntity?>
    suspend fun insertBooks(books: List<BookEntity>)
    suspend fun insertBook(book: BookEntity)
    suspend fun updateBook(book: BookEntity)
    suspend fun deleteBook(book: BookEntity)
    suspend fun deleteBookById(id: Int)
}