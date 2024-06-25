package com.example.cuteanimals.view.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuteanimals.R
import com.example.cuteanimals.databinding.ActivityMainBinding
import com.example.cuteanimals.view.adapter.CatAdapter
import com.example.cuteanimals.view.viewmodel.CatViewModel
import com.example.cuteanimals.view.viewmodel.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: CatAdapter

    private val mainViewModel: CatViewModel by lazy {
        ViewModelProvider(this)[CatViewModel::class.java]
    }

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
        collector()
        setupUI()
    }

    private fun setupUI() {
        binding.catRecycler.adapter = adapter
        binding.catRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.catRecycler.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun collector() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.mainItem.collect {
                    when (it) {
                        is UIState.Success -> {
                            binding.progress.visibility = View.GONE
                            binding.error.visibility = View.GONE
                            binding.catRecycler.visibility = View.VISIBLE
                            adapter.submitList(it.data)
                        }

                        is UIState.Failure -> {
                            binding.progress.visibility = View.GONE
                            binding.error.visibility = View.VISIBLE
                            binding.catRecycler.visibility = View.GONE
                            binding.error.text = it.throwable.toString()
                        }

                        is UIState.Loading -> {
                            binding.progress.visibility = View.VISIBLE
                            binding.error.visibility = View.GONE
                            binding.catRecycler.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }


}