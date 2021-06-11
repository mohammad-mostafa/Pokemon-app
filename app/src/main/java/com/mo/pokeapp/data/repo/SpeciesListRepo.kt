package com.mo.pokeapp.data.repo

import com.mo.pokeapp.data.dao.SpeciesListNetworkDao
import com.mo.pokeapp.data.viewobject.SpeciesListVO
import io.reactivex.Single
import javax.inject.Inject

class SpeciesListRepo @Inject constructor(private val networkDao: SpeciesListNetworkDao) {

    fun getSpeciesList(offset: Int, limit: Int): Single<List<SpeciesListVO>> {
        return networkDao.getSpeciesList(offset, limit).map {
            val response = it.list
            response.map { result ->
                SpeciesListVO(result.name, result.url)
            }
        }
    }
}