package com.morozov.testtaskforviesure.data.room

import com.morozov.testtaskforviesure.domain.Book
import com.morozov.testtaskforviesure.ui.utils.toCustomDateFormat

fun BookEntity.toDomain(): Book {
    return Book(
        id = id,
        author = author.orEmpty(),
        description = description.orEmpty(),
        image = image.orEmpty(),
        releaseDate = releaseDate.toCustomDateFormat(),
        title = title.orEmpty(),
        titlee = titlee.orEmpty()
    )
}

fun Book.toEntity(): BookEntity {
    return BookEntity(
        id = id ?: 0,
        author = author,
        description = description,
        image = image,
        releaseDate = releaseDate,
        title = title,
        titlee = titlee
    )
}