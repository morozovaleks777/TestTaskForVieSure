package com.morozov.testtaskforviesure.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0 ,
    val author: String?,
    @ColumnInfo("description")
    val description: String?,
    @ColumnInfo("image")
    val image: String?,
    @ColumnInfo(" releaseDate")
    val releaseDate: String?,
    @ColumnInfo("title")
    val title: String?,
    @ColumnInfo("titlee")
    val titlee: String?
)