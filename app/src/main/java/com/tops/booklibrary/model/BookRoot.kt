package com.tops.booklibrary.model

data class BookRoot(
    val id: Int,
    val title: String,
    val author: String,
    val image: ByteArray?,
    val isRead: String
)
