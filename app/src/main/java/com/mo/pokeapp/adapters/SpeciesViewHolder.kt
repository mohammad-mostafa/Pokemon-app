package com.mo.pokeapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.mo.pokeapp.data.viewobject.SpeciesVO
import com.mo.pokeapp.databinding.LayoutSpeciesCellBinding
import com.mo.pokeapp.utils.ImageLoader

class SpeciesViewHolder(
    private val binding: LayoutSpeciesCellBinding,
    private val callback: SpeciesViewHolderCallback?,
    private val imageLoader: ImageLoader = ImageLoader()
) :
    RecyclerView.ViewHolder(binding.root) {

    interface SpeciesViewHolderCallback {
        fun onSpeciesClicked(item: SpeciesVO?)
    }

    fun bind(item: SpeciesVO?) {
        binding.nameTv.text = item?.name

        item?.photoUrl?.let {

            imageLoader.load(it, binding.logoIv)
        }

        binding.root.setOnClickListener { callback?.onSpeciesClicked(item) }
    }
}
