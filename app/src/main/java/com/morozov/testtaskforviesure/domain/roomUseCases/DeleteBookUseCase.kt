package com.morozov.testtaskforviesure.domain.roomUseCases

import com.morozov.testtaskforviesure.data.room.toEntity
import com.morozov.testtaskforviesure.domain.Book
import com.morozov.testtaskforviesure.domain.RoomRepository
import javax.inject.Inject

class DeleteBookUseCase @Inject constructor(
    private val bookRepository: RoomRepository
) {
    suspend operator fun invoke(book: Book) {
        bookRepository.deleteBook(book.toEntity())
    }
}