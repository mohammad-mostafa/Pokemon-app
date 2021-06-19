package com.mo.pokeapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageLoader {

    fun load(url: String, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}