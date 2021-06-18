package com.mo.pokeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mo.pokeapp.data.viewobject.SpeciesVO
import com.mo.pokeapp.databinding.LayoutSpeciesCellBinding

class SpeciesListAdapter(private val callback: SpeciesViewHolder.SpeciesViewHolderCallback?) :
    PagingDataAdapter<SpeciesVO, SpeciesViewHolder>(SpeciesComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val itemBinding =
            LayoutSpeciesCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpeciesViewHolder(itemBinding, callback)
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object SpeciesComparator : DiffUtil.ItemCallback<SpeciesVO>() {

        override fun areItemsTheSame(oldItem: SpeciesVO, newItem: SpeciesVO): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: SpeciesVO, newItem: SpeciesVO): Boolean =
            oldItem == newItem
    }
}