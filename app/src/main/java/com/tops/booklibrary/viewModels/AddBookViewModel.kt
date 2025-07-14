package com.tops.booklibrary.viewModels
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tops.booklibrary.model.BookRoot

class AddBookViewModel : ViewModel(){

    private  var _books = MutableLiveData<List<BookRoot>>()
    var books: LiveData<List<BookRoot>> = _books
    private lateinit var db: SQLiteDatabase


    fun loadlistofbooks(context: Context){
        db = context.openOrCreateDatabase("library", Context.MODE_PRIVATE, null)
//        db.execSQL("CREATE TABLE IF NOT EXISTS books(id INTEGER PRIMARY KEY AUTOINCREMENT,title VARCHAR, author VARCHAR, image BLOB);")

        val booklist = mutableListOf<BookRoot>()

        val cursor = db.rawQuery("SELECT * FROM books", null)

        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(0)
                val title = cursor.getString(1)
                val author = cursor.getString(2)
                val image = cursor.getBlob(3)
                Log.d("ImageCheck", "Book ID: $id, Image ByteArray Size: ${image?.size}")
                val isreaded = cursor.getString(4)

                val book = BookRoot(id,title,author,image,isreaded)

                booklist.add(book)
            }while (cursor.moveToNext())
        }
        cursor.close()

        _books.value = booklist
    }

    fun addbooktoLibrary(context: Context,booktitle: String, bookauthor: String, bookimage: ByteArray, isread : String){

        db = context.openOrCreateDatabase("library", Context.MODE_PRIVATE, null)
        db.execSQL("""CREATE TABLE IF NOT EXISTS books(id INTEGER PRIMARY KEY AUTOINCREMENT,title VARCHAR, author VARCHAR, image BLOB, isread VARCHAR) """.trimIndent())

        val contentValue = ContentValues().apply {
            put("title",booktitle)
            put("author",bookauthor)
            put("image",bookimage)
            put("isread",isread)
        }
        db.insert("books", null , contentValue)

        loadlistofbooks(context)

    }
}