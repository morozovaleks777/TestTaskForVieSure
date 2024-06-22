package com.morozov.domain.domain.roomUseCases


import com.morozov.common.mappers.toDomainEntity
import com.morozov.common.models.Book
import com.morozov.domain.domain.RoomRepository
import javax.inject.Inject

class DeleteBookUseCase @Inject constructor(
    private val bookRepository: RoomRepository
) {
    suspend operator fun invoke(book: Book) {
        bookRepository.deleteBook(book.toDomainEntity())
    }
}