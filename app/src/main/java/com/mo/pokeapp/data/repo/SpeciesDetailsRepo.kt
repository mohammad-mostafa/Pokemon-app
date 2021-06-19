package com.mo.pokeapp.data.repo

import com.mo.pokeapp.data.dao.SpeciesDetailsDbDao
import com.mo.pokeapp.data.dao.SpeciesDetailsNetworkDao
import com.mo.pokeapp.data.database.Details
import com.mo.pokeapp.data.database.SpeciesDetailsDbEntity
import com.mo.pokeapp.data.dto.SpeciesDetailsResponse
import com.mo.pokeapp.data.sharedpreferences.AppSharedPrefs
import com.mo.pokeapp.data.viewobject.SpeciesDetailsVO
import com.mo.pokeapp.network.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class SpeciesDetailsRepo @Inject constructor(
    private val speciesDetailsNetworkDao: SpeciesDetailsNetworkDao,
    private val speciesDetailsDbDao: SpeciesDetailsDbDao,
    private val prefs: AppSharedPrefs
) {

    suspend fun getSpeciesDetails(url: String): Result<SpeciesDetailsVO> =
        withContext(Dispatchers.IO) {
            try {
                val lastNetworkCall = prefs.getLong(url, 0)
                if (prefs.getCurrentTime() - lastNetworkCall > AppSharedPrefs.SPECIES_DETAILS_CACHE_TTL) {
                    // refresh cache
                    refreshCache(url)
                }

                val entity = speciesDetailsDbDao.getDetails(url)

                Result.Success(mapDbEntityToViewObject(entity))
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    private suspend fun refreshCache(url: String) {
        val response = speciesDetailsNetworkDao.getSpeciesDetails(url)
        speciesDetailsDbDao.insert(mapNetworkResponseToDbEntity(url, response))
        prefs.putLong(url, prefs.getCurrentTime())
    }

    private fun mapDbEntityToViewObject(entity: SpeciesDetailsDbEntity): SpeciesDetailsVO {
        val flavors = entity.details.flavors.filter {
            it.lang == "en"
        }.map { it.flavorText }

        val details = entity.details

        return SpeciesDetailsVO(details.name, details.evolutionChainUrl.url, flavors)
    }

    private fun mapNetworkResponseToDbEntity(
        url: String,
        response: SpeciesDetailsResponse
    ): SpeciesDetailsDbEntity {

        val flavors = response.flavors.map {
            Details.FlavorsDb(it.flavorText, it.lang.name)
        }

        val evolutionChain = Details.EvolutionChain(response.evolutionChainUrl.url)
        val details = Details(response.name, evolutionChain, flavors)
        return SpeciesDetailsDbEntity(url, details)
    }
}