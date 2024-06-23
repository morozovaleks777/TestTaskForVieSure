package com.morozov.common


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

fun <T> List<T>.toLoadableUiState(): LoadableUiState<List<T>> {
    return when {
        true -> LoadableUiState.Available(this)
        else -> LoadableUiState.Error()
    }
}

sealed class LoadableUiState<T> {

    data class Available<T>(val data: T) : LoadableUiState<T>()

    data class Loading<T>(val message: String? = null) : LoadableUiState<T>()

    data class Error<T>(val message: String? = null) : LoadableUiState<T>()
}