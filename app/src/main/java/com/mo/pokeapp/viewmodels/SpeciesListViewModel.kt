package com.mo.pokeapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mo.pokeapp.data.repo.SpeciesListRepo
import com.mo.pokeapp.data.viewobject.SpeciesListVO
import com.mo.pokeapp.utils.Event
import com.mo.pokeapp.utils.UrlUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SpeciesListViewModel @Inject constructor(
    private val urlUtils: UrlUtils,
    private val speciesListRepo: SpeciesListRepo
) :
    ViewModel() {

    private val _navigateToDetails = MutableLiveData<Event<Int>>()
    val navigateToDetails: LiveData<Event<Int>>
        get() = _navigateToDetails

    fun fetchSpeciesList(): Flow<PagingData<SpeciesListVO>> {

        return speciesListRepo.speciesResultFlow().cachedIn(viewModelScope)
    }

    fun onSpeciesItemClicked(item: SpeciesListVO?) {
        item?.url?.let { url ->
            val id = urlUtils.getLastPathSegment(url)
            _navigateToDetails.value = Event(id)
        }
    }
}