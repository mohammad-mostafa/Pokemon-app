package com.mo.pokeapp.data.dto

import com.google.gson.annotations.SerializedName

data class SpeciesDetailsResponse(
    @SerializedName("name") val name: String,
    @SerializedName("evolution_chain") val evolutionChainUrl: EvolutionChain,
    @SerializedName("flavor_text_entries") val flavors: List<FlavorsDTO>
) {

    data class EvolutionChain(@SerializedName("url") val url: String?)

    data class FlavorsDTO(
        @SerializedName("flavor_text") val flavorText: String,
        @SerializedName("language") val lang: LanguageDTO
    )
}