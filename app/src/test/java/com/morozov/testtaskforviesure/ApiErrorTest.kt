package com.morozov.testtaskforviesure


import com.morozov.testtaskforviesure.error.ApiError
import org.junit.Assert.*
import org.junit.Test

class ApiErrorTest {

    @Test
    fun `ConnectionError should have correct message`() {
        val throwable = Throwable("Connection error")
        val error = ApiError.ConnectionError(throwable)

        assertEquals("Connection error", error.message)
        assertEquals(throwable, error.throwable)
    }

    @Test
    fun `ServerError should have correct properties`() {
        val throwable = Throwable("Server error")
        val error = ApiError.ServerError(
            throwable = throwable,
            description = "Server is down",
            code = 500,
            httpCode = 500
        )

        assertEquals("Server error", error.message)
        assertEquals(throwable, error.throwable)
        assertEquals("Server is down", error.description)
        assertEquals(500, error.code)
        assertEquals(500, error.httpCode)
    }

    @Test
    fun `ClientError should have correct properties`() {
        val throwable = Throwable("Client error")
        val error = ApiError.ClientError(
            throwable = throwable,
            description = "Invalid request",
            code = 400,
            httpCode = 400
        )

        assertEquals("Client error", error.message)
        assertEquals(throwable, error.throwable)
        assertEquals("Invalid request", error.description)
        assertEquals(400, error.code)
        assertEquals(400, error.httpCode)
    }

    @Test
    fun `Other should have correct message`() {
        val throwable = Throwable("Unknown error")
        val error = ApiError.Other(throwable)

        assertEquals("Unknown error", error.message)
        assertEquals(throwable, error.throwable)
    }
}
