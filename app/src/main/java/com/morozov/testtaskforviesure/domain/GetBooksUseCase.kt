package com.morozov.testtaskforviesure.domain

import com.morozov.testtaskforviesure.data.ApiResult
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): ApiResult<List<Book>> {
        return repository.getBooks()
    }

}