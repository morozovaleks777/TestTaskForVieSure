package com.morozov.domain.domain.roomUseCases


import com.morozov.domain.domain.models.Book
import com.morozov.domain.domain.RoomRepository
import com.morozov.domain.domain.toDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

import javax.inject.Inject


class GetBooksUseCaseFromRoom @Inject constructor(
    private val bookRepository: RoomRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<List<Book>> {
        return bookRepository.getAllBooks().mapLatest {
            books -> books.map { it.toDomain() }
        }

        }
    }
