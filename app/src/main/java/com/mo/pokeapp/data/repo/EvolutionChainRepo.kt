package com.mo.pokeapp.data.repo

import com.mo.pokeapp.data.dao.EvolutionChainNetworkDao
import com.mo.pokeapp.data.dto.EvolutionChainResponse
import com.mo.pokeapp.data.dto.EvolutionChainResponse.InnerChainDTO
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
    private val evolutionChainNetworkDao: EvolutionChainNetworkDao
) {

    suspend fun getEvolutionChain(url: String): Result<List<SpeciesVO>> =
        withContext(Dispatchers.IO) {
            try {
                val result = evolutionChainNetworkDao.getSpeciesEvolutionChain(url)
                Result.Success(mapNetworkResponseToViewObject(result))
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    private fun mapNetworkResponseToViewObject(response: EvolutionChainResponse): List<SpeciesVO> {

        if (response.chain.list.isEmpty()) return emptyList()

        val result = ArrayList<SpeciesVO>()

        val q: Queue<InnerChainDTO> = LinkedList()

        q.offer(response.chain.list[0])

        while (q.isNotEmpty()) {
            val current = q.poll()
            if (current != null) {
                val speciesDto = current.species
                val id = urlUtils.getLastPathSegment(speciesDto.url)
                val speciesVO = SpeciesVO(speciesDto.name, speciesDto.url, APIService.photoUrl(id))
                result.add(speciesVO)

                if (current.list.isNotEmpty()) {
                    q.offer(current.list[0])
                }
            }
        }

        return result
    }
}