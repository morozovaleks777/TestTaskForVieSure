package com.morozov.testtaskforviesure.data.error

import com.dailywire.thedailywire.components.api.error.ApiError
import java.net.SocketException
import java.net.UnknownHostException

object ApiErrorFactory {

    fun create(throwable: Throwable): ApiError {
        return when (throwable) {
            is UnknownHostException, is SocketException -> ApiError.ConnectionError(throwable)

            else -> ApiError.Other(throwable)
        }
    }
}