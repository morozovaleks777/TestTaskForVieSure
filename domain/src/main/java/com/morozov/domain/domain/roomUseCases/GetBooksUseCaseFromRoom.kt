package com.morozov.domain.domain.roomUseCases


import com.morozov.domain.domain.models.Book
import com.morozov.domain.domain.RoomRepository
import com.morozov.domain.domain.toDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

import javax.inject.Inject


class GetBooksUseCaseFromRoom @Inject constructor(
    private val bookRepository: RoomRepository
) {
    suspend operator fun invoke(): List<Book> {
        return bookRepository.getAllBooks()
            .map { books -> books.map { it.toDomain() } }
            .first()
    }
}
