package com.dailywire.thedailywire.components.api.error

import java.net.SocketException
import java.net.UnknownHostException

object ApiErrorFactory {

    fun create(throwable: Throwable): ApiError {
        return when (throwable) {
            is UnknownHostException, is SocketException -> ApiError.ConnectionError(throwable)

            //TODO add more cases if possible

            else -> ApiError.Other(throwable)
        }
    }
}