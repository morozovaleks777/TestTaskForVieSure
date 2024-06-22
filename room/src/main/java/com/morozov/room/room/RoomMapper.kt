package com.morozov.room.room

import com.morozov.common.models.BookDomainEntity

fun BookDomainEntity.toEntity(): BookEntity {
    return BookEntity(
        id = id,
        author = author,
        description = description,
        image = image,
        releaseDate = releaseDate,
        title = title,
        titlee = titlee
    )
}

fun BookEntity.toDomainEntity(): BookDomainEntity {
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