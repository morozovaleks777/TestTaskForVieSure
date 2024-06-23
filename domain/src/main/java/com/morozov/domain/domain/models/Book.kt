package com.morozov.domain.domain.models


import java.util.UUID


data class Book(
    val author: String,
    val description: String,
    val id: Int,
    val image: String,
    val releaseDate: String,
    val title: String,
    val titlee: String,
    val key: String = UUID.randomUUID().toString()
)