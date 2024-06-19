package com.morozov.testtaskforviesure.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val author: String?,
    val description: String?,
    val image: String?,
    val releaseDate: String?,
    val title: String?,
    val titlee: String?
)