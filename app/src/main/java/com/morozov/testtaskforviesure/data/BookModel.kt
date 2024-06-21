package com.morozov.testtaskforviesure.data

import com.morozov.testtaskforviesure.domain.Book
import com.morozov.testtaskforviesure.ui.utils.toCustomDateFormat
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

fun BookModelItem.toDomain(): Book {
    return Book(
        author = this.author,
        description = this.description,
        id = this.id,
        image = this.image,
        releaseDate = this.releaseDate.toCustomDateFormat(),
        title = this.title,
        titlee = this.titlee
    )
}