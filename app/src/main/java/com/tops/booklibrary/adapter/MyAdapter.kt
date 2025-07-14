package com.tops.booklibrary.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.tops.booklibrary.databinding.RowItemCardBinding
import com.tops.booklibrary.model.BookRoot

class MyAdapter(private val context: Context,private val booklist: List<BookRoot>): BaseAdapter() {
    override fun getCount(): Int = booklist.size

    override fun getItem(position: Int) = booklist[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(
        position : Int,
        convertView: View?,
        parent: ViewGroup?
    ): View? {

        val binding : RowItemCardBinding

        val view: View
        if (convertView == null) {
            binding = RowItemCardBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            view = convertView
            binding = view.tag as RowItemCardBinding
        }

        val book = booklist[position]
        binding.tvtitle.text = book.title
        binding.tvauthor.text = book.author
        if (book.image != null && book.image.isNotEmpty()) {
            val bitmap = BitmapFactory.decodeByteArray(book.image, 0, book.image.size)
            if (bitmap != null) {
                binding.bookImage.setImageBitmap(bitmap)
                Log.d("BookImage", "Bitmap set for book: ${book.title}")
            } else {
                Log.e("BookImage", "Failed to decode bitmap for book: ${book.title}")
            }
        } else {
            Log.e("BookImage", "Image is null or empty for book: ${book.title}")
        }

        return view
    }

}