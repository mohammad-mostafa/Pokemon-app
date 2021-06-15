package com.mo.pokeapp.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mo.pokeapp.data.SpeciesPagingSource
import com.mo.pokeapp.data.dao.SpeciesListNetworkDao
import com.mo.pokeapp.data.viewobject.SpeciesListVO
import com.mo.pokeapp.network.APIService
import com.mo.pokeapp.utils.DEFAULT_PAGE_SIZE
import com.mo.pokeapp.utils.UrlUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SpeciesListRepo @Inject constructor(
    private val urlUtils: UrlUtils,
    private val networkDao: SpeciesListNetworkDao
) {

    fun speciesResultFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<SpeciesListVO>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { SpeciesPagingSource(urlUtils, networkDao) }
        ).flow.map { pagingData ->
            pagingData.map {
                val id = urlUtils.getLastPathSegment(it.url)
                val photoUrl = APIService.photoUrl(id)
                SpeciesListVO(it.name, it.url, photoUrl)
            }
        }
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }
}