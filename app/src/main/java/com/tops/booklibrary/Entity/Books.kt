package com.tops.booklibrary.Entity

import android.text.format.DateFormat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Books(

    @PrimaryKey(autoGenerate = true) val bookId: Int,
    @ColumnInfo(name = "book_name") val bookName: String,
    @ColumnInfo(name = "author_name") val authorName: String,
    @ColumnInfo(name = "published_date") val publishedDate: DateFormat

)
