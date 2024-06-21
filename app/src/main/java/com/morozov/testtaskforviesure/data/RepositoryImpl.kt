package com.morozov.testtaskforviesure.data

import android.util.Log
import com.morozov.testtaskforviesure.domain.Book
import com.morozov.testtaskforviesure.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val service: ApiService) : Repository {

    override suspend fun getBooks(): ApiResult<List<Book>> {
        return try {
            val result = service.getBooks()
            Log.d("post", "getBooks: $result")
            ApiResult(
                success = true,
                data = result.map { it.toDomain() },
                error = null
            )
        } catch (e: Throwable) {
            ApiResult(
                success = false,
                data = null,
                error = e.message?.let { ApiError(message = it) }
            )
        }
    }
}

