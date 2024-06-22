package com.morozov.domain.domain.roomUseCases


import com.morozov.domain.domain.models.Book
import com.morozov.domain.domain.RoomRepository
import com.morozov.domain.domain.toDomain
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