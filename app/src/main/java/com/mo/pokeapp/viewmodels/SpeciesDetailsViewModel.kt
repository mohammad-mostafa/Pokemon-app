package com.mo.pokeapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mo.pokeapp.R
import com.mo.pokeapp.data.repo.EvolutionChainRepo
import com.mo.pokeapp.data.repo.SpeciesDetailsRepo
import com.mo.pokeapp.data.viewobject.SpeciesDetailsVO
import com.mo.pokeapp.data.viewobject.SpeciesVO
import com.mo.pokeapp.network.Result
import com.mo.pokeapp.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpeciesDetailsViewModel @Inject constructor(
    private val detailsRepo: SpeciesDetailsRepo,
    private val evolutionChainRepo: EvolutionChainRepo
) :
    ViewModel() {

    private var detailsUrl: String? = null

    val speciesDetails by lazy {
        MutableLiveData<SpeciesDetailsVO>().also {
            detailsUrl?.let { url -> fetchSpeciesDetails(url) }
        }
    }

    val evolutionChain by lazy {
        MutableLiveData<List<SpeciesVO>>()
    }

    val errorObservable by lazy {
        MutableLiveData<Event<Int>>()
    }

    private fun fetchSpeciesDetails(detailsUrl: String) {
        viewModelScope.launch {

            when (val result = detailsRepo.getSpeciesDetails(detailsUrl)) {
                is Result.Success -> {
                    speciesDetails.value = result.data
                    result.data.evolutionChainUrl?.let { fetchEvolutionChain(it) }
                }
                else -> errorObservable.value = Event(R.string.species_details_failure)
            }
        }
    }

    private suspend fun fetchEvolutionChain(evolutionChainUrl: String) {
        when (val result = evolutionChainRepo.getEvolutionChain(evolutionChainUrl)) {
            is Result.Success -> {
                evolutionChain.value = result.data
            }
            else -> errorObservable.value = Event(R.string.evolution_chain_failure)
        }
    }

    fun setData(detailsUrl: String?) {
        this.detailsUrl = detailsUrl
    }
}