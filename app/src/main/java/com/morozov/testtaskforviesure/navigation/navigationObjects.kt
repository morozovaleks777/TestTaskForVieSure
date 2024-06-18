package com.morozov.testtaskforviesure.navigation

import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object Books

@Serializable
data class BookDetail(
    val author: String,
    val description: String,
    val id: Int,
    val image: String,
    val releaseDate: String,
    val title: String,
    val titlee: String
)