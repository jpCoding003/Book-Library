package com.tops.booklibrary.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.booklibrary.R
import com.tops.booklibrary.adapter.BookAdapter
import com.tops.booklibrary.databinding.FragmentBooksListBinding
import com.tops.booklibrary.viewModels.AddBookViewModel


class BooksListFragment : Fragment() {
    private lateinit var binding: FragmentBooksListBinding
    private val addBookViewModel : AddBookViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBooksListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addBookViewModel.loadlistofbooks(requireContext())


        binding.rvBookList.layoutManager = LinearLayoutManager(requireContext())

        addBookViewModel.books.observe(viewLifecycleOwner){blist->
            binding.rvBookList.adapter = BookAdapter(blist)
        }


        binding.addBook.setOnClickListener {
            findNavController().navigate(R.id.action_booksListFragment_to_addBookFragment)
        }
    }
}