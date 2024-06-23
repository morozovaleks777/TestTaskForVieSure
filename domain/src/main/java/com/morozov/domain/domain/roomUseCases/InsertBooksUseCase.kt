package com.morozov.domain.domain.roomUseCases


import android.util.Log


import com.morozov.domain.domain.models.Book
import com.morozov.domain.domain.RoomRepository
import com.morozov.domain.domain.toDomainEntity
import javax.inject.Inject

class InsertBookUseCase @Inject constructor(
    private val bookRepository: RoomRepository
) {
    suspend operator fun invoke(book: Book) {
        bookRepository.insertBook(book.toDomainEntity() )
    }
}