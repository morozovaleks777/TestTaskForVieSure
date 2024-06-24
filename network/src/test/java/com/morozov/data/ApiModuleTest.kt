package com.morozov.data



import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.morozov.common.ApiResult
import com.morozov.data.network.ApiService
import com.morozov.data.network.BookModelItem
import com.morozov.data.network.RepositoryImpl
import com.morozov.domain.domain.ApiRepository
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class ApiModuleTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService
    private lateinit var apiRepository: ApiRepository

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val gson = GsonBuilder().create()
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        apiService = retrofit.create(ApiService::class.java)
        apiRepository = RepositoryImpl(apiService)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun `test getBooks failure`() = runBlocking {
        mockWebServer.enqueue(MockResponse().setResponseCode(404))

        val result = apiRepository.getBooks()

        assertTrue(!result.success)
        assertEquals(null, result.data)
        assertNotNull(result.error)
    }
}