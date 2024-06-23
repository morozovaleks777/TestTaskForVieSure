package com.morozov.domain.domain


import com.morozov.common.ApiResult
import com.morozov.domain.domain.models.Book
import javax.inject.Inject


class GetBooksUseCase @Inject constructor(private val repository: ApiRepository) {
    suspend operator fun invoke(): ApiResult<List<Book>> {
        return repository.getBooks()
    }

}

