package com.morozov.testtaskforviesure.data

import com.morozov.common.models.Book
import com.morozov.testtaskforviesure.ui.utils.toCustomDateFormat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BookModelItem(
    @SerialName("author")
    val author: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("titlee")
    val titlee: String?
)

fun BookModelItem.toDomain(): Book {
    return Book(
        author = this.author.orEmpty(),
        description = this.description.orEmpty(),
        id = this.id ?: 0,
        image = this.image.orEmpty(),
        releaseDate = this.releaseDate.toCustomDateFormat(),
        title = this.title.orEmpty(),
        titlee = this.titlee.orEmpty()
    )
}