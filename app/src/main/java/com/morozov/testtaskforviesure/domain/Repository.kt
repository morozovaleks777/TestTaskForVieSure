package com.morozov.testtaskforviesure.domain

import com.morozov.testtaskforviesure.data.ApiResult

interface Repository {
    suspend fun getBooks(): ApiResult<List<Book>>
}