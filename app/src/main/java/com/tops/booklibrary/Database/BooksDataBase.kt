package com.tops.booklibrary.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tops.booklibrary.Converters
import com.tops.booklibrary.Dao.BooksDao
import com.tops.booklibrary.Entity.Books

@Database(entities = [Books::class], version = 1)
@TypeConverters(Converters::class)
abstract class BooksDataBase : RoomDatabase() {

    abstract fun bookDao(): BooksDao
}