package com.mo.pokeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mo.pokeapp.data.viewobject.SpeciesListVO
import com.mo.pokeapp.databinding.LayoutSpeciesCellBinding

class SpeciesListAdapter : PagingDataAdapter<SpeciesListVO, SpeciesViewHolder>(SpeciesComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val itemBinding =
            LayoutSpeciesCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpeciesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object SpeciesComparator : DiffUtil.ItemCallback<SpeciesListVO>() {

        override fun areItemsTheSame(oldItem: SpeciesListVO, newItem: SpeciesListVO): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: SpeciesListVO, newItem: SpeciesListVO): Boolean =
            oldItem == newItem
    }
}