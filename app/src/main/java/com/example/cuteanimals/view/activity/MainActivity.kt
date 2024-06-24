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
import com.example.cuteanimals.view.data.model.Cat
import com.example.cuteanimals.view.data.repo.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
        val repo = RemoteRepository()
        var list :List<Cat> = listOf()
        CoroutineScope(Dispatchers.IO).launch {
            list = repo.getCatList()?.body() ?: listOf()
        }

        adapter = CatAdapter()
        binding.apply {
            catRecycler.apply {
                adapter = this@MainActivity.adapter
                layoutManager = LinearLayoutManager(context)
            }

            this.let {

                adapter?.submitList(
                    list
                )
            }
        }
    }

}