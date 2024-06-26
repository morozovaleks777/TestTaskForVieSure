package com.morozov.domain.domain

import com.morozov.common.models.BookDomainEntity
import com.morozov.domain.domain.models.Book


fun BookDomainEntity.toDomain(): Book {
    return Book(
        id = id,
        author = author.orEmpty(),
        description = description.orEmpty(),
        image = image.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        title = title.orEmpty(),
        titlee = titlee.orEmpty()
    )
}

fun Book.toDomainEntity(): BookDomainEntity {
    return BookDomainEntity(
        id = id,
        author = author,
        description = description,
        image = image,
        releaseDate = releaseDate,
        title = title,
        titlee = titlee
    )
}