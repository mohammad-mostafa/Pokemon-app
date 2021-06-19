package com.mo.pokeapp.data.repo

import com.mo.pokeapp.data.dao.EvolutionChainDbDao
import com.mo.pokeapp.data.dao.EvolutionChainNetworkDao
import com.mo.pokeapp.data.database.entity.EvolutionChainDbEntity
import com.mo.pokeapp.data.database.entity.SpeciesDb
import com.mo.pokeapp.data.dto.EvolutionChainResponse
import com.mo.pokeapp.data.sharedpreferences.AppSharedPrefs
import com.mo.pokeapp.data.viewobject.SpeciesVO
import com.mo.pokeapp.network.APIService
import com.mo.pokeapp.network.Result
import com.mo.pokeapp.utils.UrlUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class EvolutionChainRepo @Inject constructor(
    private val urlUtils: UrlUtils,
    private val evolutionChainNetworkDao: EvolutionChainNetworkDao,
    private val evolutionChainDbDao: EvolutionChainDbDao,
    private val prefs: AppSharedPrefs
) {

    suspend fun getEvolutionChain(url: String): Result<List<SpeciesVO>> =
        withContext(Dispatchers.IO) {
            try {
                val lastNetworkCall = prefs.getLong(url, 0)
                if (prefs.getCurrentTime() - lastNetworkCall > AppSharedPrefs.SPECIES_DETAILS_CACHE_TTL) {
                    // refresh cache
                    refreshCache(url)
                }

                val entity = evolutionChainDbDao.getEvolutionChain(url)

                Result.Success(mapDbEntityToViewObject(entity))
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    private suspend fun refreshCache(url: String) {
        val result = evolutionChainNetworkDao.getSpeciesEvolutionChain(url)
        evolutionChainDbDao.insert(mapNetworkResponseToEvolutionEntity(url, result))
        prefs.putLong(url, prefs.getCurrentTime())
    }

    private fun mapNetworkResponseToEvolutionEntity(
        url: String,
        response: EvolutionChainResponse
    ): EvolutionChainDbEntity {

        val chain = ArrayList<SpeciesDb>()

        if (response.chain.list.isNotEmpty()) {

            val q: Queue<EvolutionChainResponse.InnerChainDTO> = LinkedList()

            q.offer(response.chain.list[0])

            while (q.isNotEmpty()) {
                val current = q.poll()
                if (current != null) {
                    val speciesDto = current.species
                    val id = urlUtils.getLastPathSegment(speciesDto.url)
                    val speciesDb =
                        SpeciesDb(speciesDto.name, speciesDto.url, APIService.photoUrl(id))
                    chain.add(speciesDb)

                    if (current.list.isNotEmpty()) {
                        q.offer(current.list[0])
                    }
                }
            }
        }

        return EvolutionChainDbEntity(url, chain)
    }

    private fun mapDbEntityToViewObject(entity: EvolutionChainDbEntity): List<SpeciesVO> {

        return entity.list.map {
            SpeciesVO(it.name, it.url, it.photoUrl)
        }
    }
}