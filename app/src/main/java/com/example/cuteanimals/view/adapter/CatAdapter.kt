package com.example.cuteanimals.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cuteanimals.databinding.CatItemsBinding
import com.example.cuteanimals.data.model.Cat


class CatAdapter() :
    PagingDataAdapter<Cat, CatAdapter.CatViewHolder>(UrlComparator()) {

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

        fun bind(cat: Cat) {
            binding.apply {
                Glide.with(itemView.context).load(cat.url).into(catImage)
            }
        }
    }

    class UrlComparator : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat) =
            oldItem == newItem
    }
}
