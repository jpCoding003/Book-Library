package com.tops.booklibrary.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tops.booklibrary.R
import com.tops.booklibrary.databinding.FragmentAddBookBinding
import com.tops.booklibrary.viewModels.AddBookViewModel
import org.jetbrains.annotations.Contract


class AddBookFragment : Fragment() {

    private lateinit var binding: FragmentAddBookBinding
    private  val addBookViewModel: AddBookViewModel by viewModels()
    private lateinit var pickmedia: ActivityResultLauncher<PickVisualMediaRequest>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBookBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pickmedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
            uri-> if (uri != null){
                binding.bookImage.setImageURI(uri)
            }else{
            Toast.makeText(context,"Image not Found", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnimportImage.setOnClickListener {
            pickmedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }


        binding.btnaddBook.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val author = binding.etAuthor.text.toString()
            val bookimage = binding.bookImage.toString().toByteArray()
            addBookViewModel.addbooktoLibrary(requireContext(),title,author,bookimage, isread = false.toString() )
            findNavController().navigate(R.id.action_addBookFragment_to_booksListFragment)
        }
    }
}