package com.morozov.testtaskforviesure.data.room

import com.morozov.testtaskforviesure.domain.Book

fun BookEntity.toDomain(): Book {
    return Book(
        id = id,
        author = author,
        description = description,
        image = image,
        releaseDate = releaseDate,
        title = title,
        titlee = titlee
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