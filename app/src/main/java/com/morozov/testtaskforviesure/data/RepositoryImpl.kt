package com.morozov.testtaskforviesure.data

import android.util.Log
import com.morozov.common.ApiError
import com.morozov.common.ApiResult
import com.morozov.domain.domain.models.Book
import com.morozov.domain.domain.ApiRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val service: ApiService) : ApiRepository {

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
                error = ApiError("Failed to fetch books: ${e.message}")
            )
        }
    }
}



