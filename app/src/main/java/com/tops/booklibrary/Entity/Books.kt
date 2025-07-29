package com.tops.booklibrary.Entity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Books(
    @PrimaryKey(autoGenerate = true) val bookId: Int = 0,
    @ColumnInfo(name = "book_name") val bookName: String,
    @ColumnInfo(name = "author_name") val authorName: String,
    @ColumnInfo(name = "cover_page") val coverPage: Bitmap?
)
