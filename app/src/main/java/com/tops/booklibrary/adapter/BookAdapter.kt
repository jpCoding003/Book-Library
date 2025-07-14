package com.tops.booklibrary.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tops.booklibrary.R
import com.tops.booklibrary.databinding.BookCardLitemBinding
import com.tops.booklibrary.model.BookRoot
import kotlin.coroutines.coroutineContext

class BookAdapter(private val booklist: List<BookRoot>): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
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

        holder.binding.tvtitle.setText(book.title)
        holder.binding.tvauthor.setText(book.author)

//        book.image?.let {
//            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
//            holder.binding.bookImage.setImageBitmap(bitmap)
//            Log.i("ImageIS", "Image is Null")
//        } ?: run {
//            holder.binding.bookImage.setImageResource(R.drawable.ic_launcher_foreground) // fallback if image is null
//        }

        if (book.image != null && book.image.isNotEmpty()) {
            val bitmap = BitmapFactory.decodeByteArray(book.image, 0, book.image.size)
            if (bitmap != null) {
                holder.binding.bookImage.setImageBitmap(bitmap)
                Log.d("BookImage", "Bitmap set for book: ${book.title}")
            } else {
                Log.e("BookImage", "Failed to decode bitmap for book: ${book.title}")
            }
        } else {
            Log.e("BookImage", "Image is null or empty for book: ${book.title}")
        }
    }

    override fun getItemCount(): Int = booklist.size
}