package com.mo.pokeapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mo.pokeapp.data.viewobject.SpeciesVO
import com.mo.pokeapp.databinding.LayoutEvolutionCellBinding

class EvolutionChainItemViewHolder(private val binding: LayoutEvolutionCellBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SpeciesVO?) {
        binding.nameTv.text = item?.name

        Glide.with(binding.root.context)
            .load(item?.photoUrl)
            .into(binding.logoIv)
    }
}