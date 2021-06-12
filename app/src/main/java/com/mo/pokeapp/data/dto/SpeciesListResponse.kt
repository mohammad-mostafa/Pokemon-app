package com.mo.pokeapp.data.dto

import com.google.gson.annotations.SerializedName

data class SpeciesListResponse(@SerializedName("results") val list: List<SpeciesResult>) {

}