package com.tops.booklibrary.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.booklibrary.R
import com.tops.booklibrary.adapter.BookAdapter
import com.tops.booklibrary.databinding.FragmentPendingBooksBinding
import com.tops.booklibrary.viewModels.BooksViewModel
import kotlin.getValue

class PendingBooksFragment : Fragment() {

    private lateinit var binding: FragmentPendingBooksBinding

    private val booksViewModel : BooksViewModel by viewModels()
    private  lateinit var adapter :  BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPendingBooksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        booksViewModel.getBooks(requireContext())
        binding.rvBookList.layoutManager = LinearLayoutManager(requireContext())
        adapter = BookAdapter(mutableListOf())

        booksViewModel.listBooks.observe(viewLifecycleOwner, Observer{
            list-> adapter.submitList(list)
        })

        binding.rvBookList.adapter = adapter
        binding.addBook.setOnClickListener {
            findNavController().navigate(R.id.action_pendingBooksFragment_to_addBookFragment)
        }

    }
}