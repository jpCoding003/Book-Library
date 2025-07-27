package com.tops.booklibrary.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tops.booklibrary.Dao.BooksDao
import com.tops.booklibrary.Entity.Books

@Database(entities = [Books::class], version = 1)
abstract class BooksDataBase : RoomDatabase() {
    abstract fun bookDao(): BooksDao
}