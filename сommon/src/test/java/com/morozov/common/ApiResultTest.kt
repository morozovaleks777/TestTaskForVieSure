package com.morozov.common


import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ApiResultTest {

    @Test
    fun `test successful ApiResult`() {
        val data = "Success data"
        val apiResult = ApiResult(success = true, data = data, error = null)

        assertEquals(true, apiResult.success)
        assertEquals(data, apiResult.data)
        assertEquals(null, apiResult.error)
    }

    @Test
    fun `test unsuccessful ApiResult`() {

        val errorMessage = "Error message"
        val apiError = ApiError(errorMessage)
        val apiResult = ApiResult(success = false, data = null, error = apiError)

        assertEquals(false, apiResult.success)
        assertNull(null, apiResult.data)
        assertEquals(apiError, apiResult.error)
    }
}
