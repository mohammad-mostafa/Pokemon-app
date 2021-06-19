package com.mo.pokeapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.mo.pokeapp.databinding.LayoutTitleCellBinding

class TitleViewHolder(private val binding: LayoutTitleCellBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.titleTv.text = item
    }
}