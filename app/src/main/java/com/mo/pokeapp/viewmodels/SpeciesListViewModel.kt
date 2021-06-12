package com.mo.pokeapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mo.pokeapp.data.repo.SpeciesListRepo
import com.mo.pokeapp.data.viewobject.SpeciesListVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SpeciesListViewModel @Inject constructor(private val speciesListRepo: SpeciesListRepo) :
    ViewModel() {

    fun fetchSpeciesList(): Flow<PagingData<SpeciesListVO>> {

        return speciesListRepo.speciesResultFlow().cachedIn(viewModelScope)
    }
}