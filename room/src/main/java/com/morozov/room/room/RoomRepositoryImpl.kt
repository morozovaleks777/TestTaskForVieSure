package com.morozov.room.room

import com.morozov.common.models.BookDomainEntity
import com.morozov.domain.domain.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RoomRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
) : RoomRepository {
    override fun getAllBooks(): Flow<List<BookDomainEntity>> =
        bookDao.getAllBooks().map { entities ->
            entities.map { entity -> entity.toDomainEntity() }
        }

    override fun getBookById(id: Int): Flow<BookDomainEntity?> =
        bookDao.getBookById(id).map { entity ->
            entity?.toDomainEntity()
        }

    override suspend fun insertBooks(books: List<BookDomainEntity>) =
        bookDao.insertBooks(books.map { it.toEntity() })

    override suspend fun insertBook(book: BookDomainEntity) =
        bookDao.insertBook(book.toEntity())

    override suspend fun updateBook(book: BookDomainEntity) =
        bookDao.updateBook(book.toEntity())

    override suspend fun deleteBook(book: BookDomainEntity) =
        bookDao.deleteBook(book.toEntity())

    override suspend fun deleteBookById(id: Int) =
        bookDao.deleteBookById(id)
}