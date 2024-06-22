package com.morozov.domain.domain

import com.morozov.common.ApiResult
import com.morozov.common.models.Book
import com.morozov.testtaskforviesure.domain.ApiRepository
import javax.inject.Inject


class GetBooksUseCase @Inject constructor(private val repository: ApiRepository) {
    suspend operator fun invoke(): ApiResult<List<Book>> {
        return repository.getBooks()
    }

}

