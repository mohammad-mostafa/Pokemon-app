package com.mo.pokeapp.data.dto

import com.google.gson.annotations.SerializedName

data class SpeciesListResponse(
    @SerializedName("next") val nextUrl: String?,
    @SerializedName("results") val list: List<SpeciesResult>
)