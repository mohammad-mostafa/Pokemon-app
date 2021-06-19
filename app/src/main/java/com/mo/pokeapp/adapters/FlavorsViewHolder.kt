package com.mo.pokeapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.mo.pokeapp.databinding.LayoutFlavorCellBinding

class FlavorsViewHolder(private val binding: LayoutFlavorCellBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.flavorTv.text = item
    }
}