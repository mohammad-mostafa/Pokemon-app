package com.mo.pokeapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.mo.pokeapp.data.viewobject.SpeciesVO
import com.mo.pokeapp.databinding.LayoutEvolutionCellBinding
import com.mo.pokeapp.utils.ImageLoader

class EvolutionChainItemViewHolder(
    private val binding: LayoutEvolutionCellBinding,
    private val imageLoader: ImageLoader = ImageLoader()
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SpeciesVO?) {
        binding.nameTv.text = item?.name

        item?.photoUrl?.let {
            imageLoader.load(it, binding.logoIv)
        }
    }
}