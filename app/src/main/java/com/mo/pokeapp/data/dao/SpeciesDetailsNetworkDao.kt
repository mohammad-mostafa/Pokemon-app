package com.mo.pokeapp.data.dao

import com.mo.pokeapp.data.dto.SpeciesDetailsResponse
import com.mo.pokeapp.network.APIService
import javax.inject.Inject

class SpeciesDetailsNetworkDao @Inject constructor(private val apiService: APIService) {

    suspend fun getSpeciesDetails(url: String): SpeciesDetailsResponse =
        apiService.getSpeciesDetails(url)
}