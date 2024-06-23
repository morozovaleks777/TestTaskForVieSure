package com.morozov.domain.domain

import com.morozov.common.models.BookDomainEntity

import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    fun getAllBooks(): Flow<List<BookDomainEntity>>
    fun getBookById(id: Int): Flow<BookDomainEntity?>
    suspend fun insertBooks(books: List<BookDomainEntity>)
    suspend fun insertBook(book: BookDomainEntity)
    suspend fun updateBook(book: BookDomainEntity)
    suspend fun deleteBook(book: BookDomainEntity)
    suspend fun deleteBookById(id: Int)
}