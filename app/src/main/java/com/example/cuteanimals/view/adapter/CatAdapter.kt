package com.example.cuteanimals.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cuteanimals.databinding.CatItemsBinding


class CatAdapter() :
    ListAdapter<String, CatAdapter.CatViewHolder>(UrlComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding =
            CatItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class CatViewHolder(private val binding: CatItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(url: String) {
            binding.apply {
                Glide.with(itemView.context).load(url).into(catImage)
            }
        }
    }

    class UrlComparator : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }
}
