package com.morozov.data.network

import retrofit2.http.GET

interface ApiService {
   @GET(".")  // Adjust the endpoint based on the actual URL
    suspend fun getBooks(): List<com.morozov.data.network.BookModelItem>
}