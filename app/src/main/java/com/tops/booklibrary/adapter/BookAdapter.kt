package com.tops.booklibrary.adapter

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.booklibrary.Entity.Books
import com.tops.booklibrary.databinding.BookCardLitemBinding
import com.tops.booklibrary.model.BookRoot

class BookAdapter(private var booklist: MutableList<Books>): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    class BookViewHolder(val binding: BookCardLitemBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder {
        val binding = BookCardLitemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BookViewHolder,
        position: Int
    ) {
        val book = booklist[position]

        holder.binding.tvtitle.setText(book.bookName)
        holder.binding.tvauthor.setText(book.authorName)

//        if (book.image != null && book.image.isNotEmpty()) {
//            val bitmap = BitmapFactory.decodeByteArray(book.image, 0, book.image.size)
//            if (bitmap != null) {
//                holder.binding.bookImage.setImageBitmap(bitmap)
//                Log.d("BookImage", "Bitmap set for book: ${book.title}")
//            } else {
//                Log.e("BookImage", "Failed to decode bitmap for book: ${book.title}")
//            }
//        } else {
//            Log.e("BookImage", "Image is null or empty for book: ${book.title}")
//        }


        Log.d("BooksLoaded", "Loaded ${booklist.size} books from DB")
    }

    fun submitList(list: MutableList<Books>){
        booklist = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = booklist.size
}