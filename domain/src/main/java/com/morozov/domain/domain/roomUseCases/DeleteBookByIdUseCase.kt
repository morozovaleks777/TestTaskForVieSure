package com.morozov.domain.domain.roomUseCases

import com.morozov.domain.domain.RoomRepository
import javax.inject.Inject

class DeleteBookByIdUseCase @Inject constructor(
    private val bookRepository: RoomRepository
) {
    suspend operator fun invoke(id: Int) {
        bookRepository.deleteBookById(id)
    }
}