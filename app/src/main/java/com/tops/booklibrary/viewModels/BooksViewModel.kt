package com.tops.booklibrary.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.tops.booklibrary.Database.BooksDataBase
import com.tops.booklibrary.Entity.Books

class BooksViewModel: ViewModel() {

    private  var _listBooks = MutableLiveData<MutableList<Books>>()
     var listBooks : LiveData<MutableList<Books>> = _listBooks

    fun getBooks(context: Context){

        val db = Room.databaseBuilder(context, BooksDataBase::class.java,"library").build()
        val booksDao =db.bookDao()
        val books : MutableList<Books> = booksDao.getAll()

        _listBooks.value = books.toMutableList()
    }

}