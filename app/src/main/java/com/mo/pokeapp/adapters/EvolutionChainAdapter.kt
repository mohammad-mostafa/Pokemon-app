package com.mo.pokeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mo.pokeapp.data.viewobject.SpeciesVO
import com.mo.pokeapp.databinding.LayoutEvolutionCellBinding

class EvolutionChainAdapter :
    ListAdapter<SpeciesVO, EvolutionChainItemViewHolder>(SpeciesComparator) {

    object SpeciesComparator : DiffUtil.ItemCallback<SpeciesVO>() {

        override fun areItemsTheSame(oldItem: SpeciesVO, newItem: SpeciesVO): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: SpeciesVO, newItem: SpeciesVO): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EvolutionChainItemViewHolder {
        val itemBinding =
            LayoutEvolutionCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EvolutionChainItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EvolutionChainItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}