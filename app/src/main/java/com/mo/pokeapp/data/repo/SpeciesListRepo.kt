package com.mo.pokeapp.data.repo

import android.net.Uri
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mo.pokeapp.data.SpeciesPagingSource
import com.mo.pokeapp.data.dao.SpeciesListNetworkDao
import com.mo.pokeapp.data.viewobject.SpeciesListVO
import com.mo.pokeapp.utils.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SpeciesListRepo @Inject constructor(private val networkDao: SpeciesListNetworkDao) {

    fun speciesResultFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<SpeciesListVO>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { SpeciesPagingSource(networkDao) }
        ).flow.map { pagingData ->
            pagingData.map {
                val uri: Uri = Uri.parse(it.url)
                val id: String? = uri.lastPathSegment
                val photoUrl =
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
                SpeciesListVO(it.name, it.url, photoUrl)
            }
        }
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }
}