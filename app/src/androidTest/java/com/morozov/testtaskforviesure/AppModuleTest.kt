package com.morozov.testtaskforviesure

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.morozov.testtaskforviesure.data.ApiService
import com.morozov.testtaskforviesure.di.AppModule
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class AppModuleTest {

    private lateinit var context: Context
    private lateinit var cache: Cache
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        cache = AppModule.provideCache(context)
        okHttpClient = AppModule.provideOkhttpBuilder(cache)
        retrofit = AppModule.provideRetrofit(okHttpClient)
        apiService = AppModule.provideApiService(retrofit)
    }

    @Test
    fun testProvideCache() {
        assertNotNull(cache)
    }

    @Test
    fun testProvideOkhttpBuilder() {
        assertNotNull(okHttpClient)
    }


    @Test
    fun testProvideRetrofit() {
        assertNotNull(retrofit)
    }
    @Test
    fun testProvideApiService() {
        assertNotNull(apiService)
    }

}