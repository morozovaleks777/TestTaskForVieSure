package com.morozov.common.models

data class BookDomainEntity(
    val id: Int = 0,
    val author: String?,
    val description: String?,
    val image: String?,
    val releaseDate: String?,
    val title: String?,
    val titlee: String?
)