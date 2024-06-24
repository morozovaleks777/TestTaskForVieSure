package com.morozov.data.network.error

sealed class ApiError {
    abstract val throwable: Throwable

    open val message: String
        get() = throwable.localizedMessage.orEmpty()

    data class ConnectionError(override val throwable: Throwable) : ApiError()

    data class ServerError(
        override val throwable: Throwable,
        val description: String,
        val code: Int,
        val httpCode: Int,
    ) : ApiError() {
        companion object {
            const val UNKNOWN_CODE = -1
        }
    }

    data class ClientError(
        override val throwable: Throwable,
        val description: String,
        val code: Int,
        val httpCode: Int,
    ) : ApiError() {
        companion object {
            const val UNKNOWN_CODE = -1
        }
    }

    data class Other(override val throwable: Throwable) : ApiError()
}