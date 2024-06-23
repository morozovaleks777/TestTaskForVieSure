package com.morozov.testtaskforviesure

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.morozov.data.network.ApiService
import com.morozov.data.network.AppModule
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
    private lateinit var apiService: com.morozov.data.network.ApiService

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        cache = com.morozov.data.network.AppModule.provideCache(context)
        okHttpClient = com.morozov.data.network.AppModule.provideOkhttpBuilder(cache)
        retrofit = com.morozov.data.network.AppModule.provideRetrofit(okHttpClient)
        apiService = com.morozov.data.network.AppModule.provideApiService(retrofit)
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