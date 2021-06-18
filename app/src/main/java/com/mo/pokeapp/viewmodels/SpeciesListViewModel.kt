package com.mo.pokeapp.viewmodels

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.mo.pokeapp.data.repo.SpeciesListRepo
import com.mo.pokeapp.data.viewobject.SpeciesVO
import com.mo.pokeapp.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpeciesListViewModel @Inject constructor(
    private val speciesListRepo: SpeciesListRepo
) : ViewModel() {

    private val _navigateToDetails = MutableLiveData<Event<String>>()
    val navigateToDetails: LiveData<Event<String>>
        get() = _navigateToDetails

    val speciesList by lazy {
        speciesListRepo.speciesResultFlow().cachedIn(viewModelScope).asLiveData()
    }

    fun onSpeciesItemClicked(item: SpeciesVO?) {
        item?.url?.let { url ->
            _navigateToDetails.value = Event(url)
        }
    }
}