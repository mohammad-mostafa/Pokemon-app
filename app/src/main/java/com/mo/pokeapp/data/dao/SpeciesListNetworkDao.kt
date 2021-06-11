package com.mo.pokeapp.data.dao

import com.mo.pokeapp.data.dto.SpeciesListResponse
import com.mo.pokeapp.network.APIService
import io.reactivex.Single
import javax.inject.Inject

class SpeciesListNetworkDao @Inject constructor(private val apiService: APIService) {

    fun getSpeciesList(offset: Int = 0, limit: Int = 20): Single<SpeciesListResponse> =
        apiService.getSpecies(offset, limit)
}