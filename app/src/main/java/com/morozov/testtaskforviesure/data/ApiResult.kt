package com.morozov.testtaskforviesure.data

import androidx.compose.runtime.Stable


data class ApiResult<T>(
    val success: Boolean,
    val data: T?,
    val error: ApiError?
)

data class ApiError(
    val message: String
)

fun <T> ApiResult<T>.toLoadableUiState(): LoadableUiState<T> {
    return when {
        this.success && this.data != null -> LoadableUiState.Available(this.data)
        else -> LoadableUiState.Error(this.error?.message)
    }
}

sealed class LoadableUiState<T> {
    @Stable
    data class Available<T>(val data: T) : LoadableUiState<T>()

    @Stable
    data class Loading<T>(val message: String? = null) : LoadableUiState<T>()

    @Stable
    data class Error<T>(val message: String? = null) : LoadableUiState<T>()
}