    package com.tops.booklibrary.viewModels

    import android.content.ContentValues
    import android.content.Context
    import android.graphics.Bitmap
    import androidx.lifecycle.LiveData
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import androidx.room.Room
    import com.tops.booklibrary.Database.BooksDataBase
    import com.tops.booklibrary.Entity.Books
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.launch

    class BooksViewModel: ViewModel() {

        private  var _listBooks = MutableLiveData<MutableList<Books>>()
         var listBooks : LiveData<MutableList<Books>> = _listBooks


        fun getBooks(context: Context){

            val db = Room.databaseBuilder(context, BooksDataBase::class.java,"library").build()
            val booksDao =db.bookDao()

            // Use coroutine for background work
            viewModelScope.launch(Dispatchers.IO) {
                val books: MutableList<Books> = booksDao.getAll().toMutableList()
                _listBooks.postValue(books)
            }
        }

        fun addBooks(context: Context,title: String, author: String, bookimage: Bitmap){
            val db = Room.databaseBuilder(context, BooksDataBase::class.java, "library").build()
            val booksDao = db.bookDao()

            val newBook = Books(
             // Room will auto-generate it
                bookName = title,
                authorName = author, // set current date, or pass one if needed
                coverPage = bookimage
            )

            viewModelScope.launch(Dispatchers.IO) {
                booksDao.insertBook(newBook)

                // Update LiveData after insertion
                val updatedList = booksDao.getAll().toMutableList()
                _listBooks.postValue(updatedList)
            }
        }
    }