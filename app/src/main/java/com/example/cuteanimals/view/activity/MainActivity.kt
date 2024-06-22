package com.example.cuteanimals.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuteanimals.R
import com.example.cuteanimals.databinding.ActivityMainBinding
import com.example.cuteanimals.view.adapter.CatAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var adapter: CatAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter = CatAdapter()
        binding.apply {
            catRecycler.apply {
                adapter = this@MainActivity.adapter
                layoutManager = LinearLayoutManager(context)
            }

            this.let {

                adapter?.submitList(
                    listOf(
                        "https://cdn2.thecatapi.com/images/46j.jpg",
                        "https://cdn2.thecatapi.com/images/5pl.jpg",
                        "https://cdn2.thecatapi.com/images/acn.jpg",
                        "https://cdn2.thecatapi.com/images/bu3.jpg",
                        "https://cdn2.thecatapi.com/images/c9g.jpg",
                        "https://cdn2.thecatapi.com/images/d80.png",
                        "https://cdn2.thecatapi.com/images/MTYxMjc1OQ.jpg",
                        "https://cdn2.thecatapi.com/images/MTg3Nzk5MQ.jpg",
                        "https://cdn2.thecatapi.com/images/MTg3OTI3MQ.jpg",
                        "https://cdn2.thecatapi.com/images/LIQSvUemz.jpg"
                    )
                )
            }
        }
    }

}