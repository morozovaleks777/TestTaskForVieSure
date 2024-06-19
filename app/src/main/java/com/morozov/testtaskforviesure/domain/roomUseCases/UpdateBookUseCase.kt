package com.morozov.testtaskforviesure.domain.roomUseCases

import com.morozov.testtaskforviesure.data.room.toEntity
import com.morozov.testtaskforviesure.domain.Book
import com.morozov.testtaskforviesure.domain.RoomRepository
import javax.inject.Inject


class UpdateBookUseCase @Inject constructor(
    private val bookRepository: RoomRepository
) {
    suspend operator fun invoke(book: Book) {
        bookRepository.updateBook(book.toEntity())
    }
}