package com.mo.pokeapp.data.dto

import com.google.gson.annotations.SerializedName

data class SpeciesDetailsResponse(
    @SerializedName("name") val name: String,
    @SerializedName("evolution_chain") val evolutionChainUrl: EvolutionChain
) {

    data class EvolutionChain(@SerializedName("url") val url: String?)
}