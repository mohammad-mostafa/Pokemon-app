package com.mo.pokeapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mo.pokeapp.data.viewobject.SpeciesVO
import com.mo.pokeapp.databinding.LayoutSpeciesCellBinding

class SpeciesViewHolder(
    private val binding: LayoutSpeciesCellBinding,
    private val callback: SpeciesViewHolderCallback?
) :
    RecyclerView.ViewHolder(binding.root) {

    interface SpeciesViewHolderCallback {
        fun onSpeciesClicked(item: SpeciesVO?)
    }

    fun bind(item: SpeciesVO?) {
        binding.nameTv.text = item?.name

        Glide.with(binding.root.context)
            .load(item?.photoUrl)
            .into(binding.logoIv)

        binding.root.setOnClickListener { callback?.onSpeciesClicked(item) }
    }
}
