package com.morozov.testtaskforviesure.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable




@Serializable
data class BookModelItem(
    @SerialName("author")
    val author: String,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("title")
    val title: String,
    @SerialName("titlee")
    val titlee: String
)