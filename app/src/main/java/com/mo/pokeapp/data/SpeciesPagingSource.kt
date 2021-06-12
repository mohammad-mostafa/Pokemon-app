package com.mo.pokeapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mo.pokeapp.data.dao.SpeciesListNetworkDao
import com.mo.pokeapp.data.dto.SpeciesResult
import com.mo.pokeapp.utils.DEFAULT_PAGE_SIZE
import javax.inject.Inject

class SpeciesPagingSource @Inject constructor(private val speciesListNetworkDao: SpeciesListNetworkDao) :
    PagingSource<Int, SpeciesResult>() {

    override fun getRefreshKey(state: PagingState<Int, SpeciesResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SpeciesResult> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = if (params.key != null) params.key else 0
            val response = speciesListNetworkDao.getSpeciesList(nextPageNumber!!, DEFAULT_PAGE_SIZE)
            return LoadResult.Page(
                data = response.list,
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber + 20
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}