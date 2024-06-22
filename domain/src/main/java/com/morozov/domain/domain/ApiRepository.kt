package com.morozov.domain.domain



import com.morozov.common.ApiResult
import com.morozov.domain.domain.models.Book


interface ApiRepository {
    suspend fun getBooks(): ApiResult<List<Book>>
}