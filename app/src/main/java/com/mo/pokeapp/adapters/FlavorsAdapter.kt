package com.mo.pokeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mo.pokeapp.databinding.LayoutFlavorCellBinding

class FlavorsAdapter : ListAdapter<String, FlavorsViewHolder>(FlavorsComparator) {

    object FlavorsComparator : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlavorsViewHolder {
        val binding = LayoutFlavorCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlavorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlavorsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}