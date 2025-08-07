package com.tops.booklibrary

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.tops.booklibrary.adapter.ViewPagerAdapter
import com.tops.booklibrary.databinding.ActivityMainBinding
import com.tops.booklibrary.fragments.BooksListFragment
import com.tops.booklibrary.fragments.PendingBooksFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewpageradapter: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val listoffragments = listOf(BooksListFragment(), PendingBooksFragment())

        viewpageradapter = ViewPagerAdapter(listoffragments, supportFragmentManager ,lifecycle)

        binding.viewpager.adapter = viewpageradapter

        TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->
            tab.text = when(position) {
                0 -> "All Books"
                1 -> "Pending"
                else -> " "
            }
        }.attach()
    }
}