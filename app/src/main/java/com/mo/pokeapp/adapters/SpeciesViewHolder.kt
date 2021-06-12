package com.mo.pokeapp.adapters

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mo.pokeapp.R
import com.mo.pokeapp.data.viewobject.SpeciesListVO
import java.net.URI

class SpeciesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: SpeciesListVO?) {
        view.findViewById<TextView>(R.id.nameTv).text = item?.name

        item?.url?.let {

            val uri: Uri = Uri.parse(item.url)
            val id: String? = uri.lastPathSegment

            val iv = view.findViewById<ImageView>(R.id.logoIv)
            Glide.with(view.context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png")
                .into(iv)
        }
    }
}
