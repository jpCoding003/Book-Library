package com.tops.booklibrary.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.booklibrary.Entity.Books
import com.tops.booklibrary.databinding.BookCardLitemBinding

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


        Log.d("BooksLoaded", "Loaded ${booklist.size} books from DB")
    }

    fun submitList(list: MutableList<Books>){
        booklist = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = booklist.size
}