package com.morozov.testtaskforviesure.domain

import com.morozov.common.ApiResult
import com.morozov.common.models.Book


interface ApiRepository {
    suspend fun getBooks(): ApiResult<List<Book>>
}