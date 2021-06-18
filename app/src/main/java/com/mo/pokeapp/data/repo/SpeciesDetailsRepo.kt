package com.mo.pokeapp.data.repo

import com.mo.pokeapp.data.dao.SpeciesDetailsNetworkDao
import com.mo.pokeapp.data.dto.SpeciesDetailsResponse
import com.mo.pokeapp.data.viewobject.SpeciesDetailsVO
import com.mo.pokeapp.network.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class SpeciesDetailsRepo @Inject constructor(private val speciesDetailsNetworkDao: SpeciesDetailsNetworkDao) {

    suspend fun getSpeciesDetails(url: String): Result<SpeciesDetailsVO> =
        withContext(Dispatchers.IO) {
            try {
                val response = speciesDetailsNetworkDao.getSpeciesDetails(url)
                Result.Success(mapNetworkResponseToViewObject(response))
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    private fun mapNetworkResponseToViewObject(response: SpeciesDetailsResponse): SpeciesDetailsVO {
        return SpeciesDetailsVO(response.name, response.evolutionChainUrl.url)
    }
}