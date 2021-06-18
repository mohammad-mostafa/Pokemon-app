package com.mo.pokeapp.data.dao

import com.mo.pokeapp.data.dto.EvolutionChainResponse
import com.mo.pokeapp.network.APIService
import javax.inject.Inject

class EvolutionChainNetworkDao @Inject constructor(private val apiService: APIService) {

    suspend fun getSpeciesEvolutionChain(url: String): EvolutionChainResponse =
        apiService.getEvolutionChain(url)
}