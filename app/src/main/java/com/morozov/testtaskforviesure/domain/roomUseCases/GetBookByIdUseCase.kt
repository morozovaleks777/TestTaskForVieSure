package com.morozov.testtaskforviesure.domain.roomUseCases

import com.morozov.testtaskforviesure.data.room.toDomain
import com.morozov.testtaskforviesure.domain.Book
import com.morozov.testtaskforviesure.domain.RoomRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetBookByIdUseCase @Inject constructor(
    private val bookRepository: RoomRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(id: Int): Flow<Book?> {
        return bookRepository.getBookById(id).mapLatest {book -> book?.toDomain() }
    }
}