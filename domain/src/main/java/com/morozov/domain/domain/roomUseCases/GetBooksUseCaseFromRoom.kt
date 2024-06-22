package com.morozov.testtaskforviesure.domain.roomUseCases

import com.morozov.common.mappers.toDomain
import com.morozov.common.models.Book
import com.morozov.domain.domain.RoomRepository
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
