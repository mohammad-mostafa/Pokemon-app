package com.mo.pokeapp.data.dto

import com.google.gson.annotations.SerializedName

data class SpeciesResult(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)