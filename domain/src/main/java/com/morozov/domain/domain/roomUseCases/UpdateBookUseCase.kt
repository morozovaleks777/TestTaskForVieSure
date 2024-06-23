package com.morozov.domain.domain.roomUseCases


import com.morozov.domain.domain.models.Book
import com.morozov.domain.domain.RoomRepository
import com.morozov.domain.domain.toDomainEntity
import javax.inject.Inject


class UpdateBookUseCase @Inject constructor(
    private val bookRepository: RoomRepository
) {
    suspend operator fun invoke(book: Book) {
        bookRepository.updateBook(book.toDomainEntity())
    }
}