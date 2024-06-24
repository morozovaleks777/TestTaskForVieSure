package com.morozov.data


import com.morozov.common.ApiError
import com.morozov.common.ApiResult
import com.morozov.common.LoadableUiState
import com.morozov.common.toLoadableUiState
import org.junit.Assert.assertEquals
import org.junit.Test

class ApiResultExtensionsTest {

    @Test
    fun `test toLoadableUiState() success with data`() {

        val data = "Success data"
        val apiResult = ApiResult(success = true, data = data, error = null)


        val loadableUiState = apiResult.toLoadableUiState()

        assertEquals(true, loadableUiState is LoadableUiState.Available)
        assertEquals(data, (loadableUiState as LoadableUiState.Available).data)
    }

    @Test
    fun `test toLoadableUiState() error`() {

        val errorMessage = "Error message"
        val apiError = ApiError(errorMessage)

        val apiResult = ApiResult(success = false, data = null, error = apiError)

        val loadableUiState = apiResult.toLoadableUiState()


        assertEquals(true, loadableUiState is LoadableUiState.Error)
        assertEquals(errorMessage, (loadableUiState as LoadableUiState.Error).message)
    }
}
