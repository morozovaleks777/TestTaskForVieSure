package com.morozov.data.network.error


//import java.net.SocketException
//import java.net.UnknownHostException
//
//object ApiErrorFactory {
//
//    fun create(throwable: Throwable): ApiError {
//        return when (throwable) {
//            is UnknownHostException, is SocketException -> ApiError.ConnectionError(throwable)
//
//            else -> ApiError.Other(throwable)
//        }
//    }
//}