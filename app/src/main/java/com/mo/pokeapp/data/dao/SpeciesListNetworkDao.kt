package com.mo.pokeapp.data.dao

import com.mo.pokeapp.data.dto.SpeciesListResponse
import com.mo.pokeapp.network.APIService
import javax.inject.Inject

class SpeciesListNetworkDao @Inject constructor(private val apiService: APIService) {

    suspend fun getSpeciesList(offset: Int = 0, limit: Int = 20): SpeciesListResponse =
        apiService.getSpecies(offset, limit)
}