package com.mo.pokeapp.data.dto

import com.google.gson.annotations.SerializedName

data class EvolutionChainResponse(@SerializedName("chain") val chain: ChainDTO) {

    data class ChainDTO(@SerializedName("evolves_to") val list: List<InnerChainDTO>)

    data class InnerChainDTO(
        @SerializedName("species") val species: SpeciesResult,
        @SerializedName("evolves_to") val list: List<InnerChainDTO>
    )
}
