package com.tops.booklibrary.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tops.booklibrary.Entity.Books

@Dao
interface BooksDao {

    @Query("SELECT * FROM books")
    fun getAll(): MutableList<Books>

    @Insert
    suspend fun insertBook(books: Books)

}