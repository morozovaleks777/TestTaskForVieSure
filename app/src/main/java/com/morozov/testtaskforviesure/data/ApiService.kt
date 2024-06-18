package com.morozov.testtaskforviesure.data

import retrofit2.http.GET

interface ApiService {
   @GET(".")  // Adjust the endpoint based on the actual URL
    suspend fun getBooks(): List<BookModelItem>
}