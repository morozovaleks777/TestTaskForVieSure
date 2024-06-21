package com.morozov.testtaskforviesure.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0 ,
    val author: String?,
    @ColumnInfo
    val description: String?,
    @ColumnInfo
    val image: String?,
    @ColumnInfo
    val releaseDate: String?,
    @ColumnInfo
    val title: String?,
    @ColumnInfo
    val titlee: String?
)