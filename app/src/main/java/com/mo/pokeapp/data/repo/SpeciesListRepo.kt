package com.mo.pokeapp.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mo.pokeapp.data.SpeciesPagingSource
import com.mo.pokeapp.data.dao.SpeciesListNetworkDao
import com.mo.pokeapp.data.dto.SpeciesListResponse
import com.mo.pokeapp.data.dto.SpeciesResult
import com.mo.pokeapp.data.viewobject.SpeciesListVO
import com.mo.pokeapp.network.Result
import com.mo.pokeapp.utils.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SpeciesListRepo @Inject constructor(private val networkDao: SpeciesListNetworkDao) {

//    suspend fun getSpeciesList(offset: Int, limit: Int): Result<List<SpeciesListVO>> =
//        withContext(Dispatchers.IO) {
//            val response = networkDao.getSpeciesList(offset, limit)
//            try {
//                Result.Success(mapNetworkResponseToVO(response))
//            } catch (ex: Exception) {
//                Result.Error(ex)
//            }
//        }

    private fun mapNetworkResponseToVO(response: SpeciesListResponse): List<SpeciesListVO> =
        response.list.map {
            SpeciesListVO(it.name, it.url)
        }

    fun speciesResultFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<SpeciesListVO>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { SpeciesPagingSource(networkDao) }
        ).flow.map { pagingData ->
            pagingData.map {
                SpeciesListVO(it.name, it.url)
            }
        }
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }
}