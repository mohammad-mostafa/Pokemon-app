package com.mo.pokeapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mo.pokeapp.data.viewobject.SpeciesListVO
import com.mo.pokeapp.databinding.LayoutSpeciesCellBinding

class SpeciesViewHolder(private val binding: LayoutSpeciesCellBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SpeciesListVO?) {
        binding.nameTv.text = item?.name

        Glide.with(binding.root.context)
            .load(item?.photoUrl)
            .into(binding.logoIv)
    }
}
