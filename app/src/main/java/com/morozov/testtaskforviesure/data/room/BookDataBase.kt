package com.morozov.testtaskforviesure.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [BookEntity::class], version = 4, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

}