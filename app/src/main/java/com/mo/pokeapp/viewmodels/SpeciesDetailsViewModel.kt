package com.mo.pokeapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mo.pokeapp.data.repo.SpeciesDetailsRepo
import com.mo.pokeapp.data.viewobject.SpeciesDetailsVO
import com.mo.pokeapp.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpeciesDetailsViewModel @Inject constructor(private val detailsRepo: SpeciesDetailsRepo) :
    ViewModel() {

    private var detailsUrl: String? = null

    val speciesDetails by lazy {
        MutableLiveData<SpeciesDetailsVO>().also {
            detailsUrl?.let { url -> fetchSpeciesDetails(url) }
        }
    }

    private fun fetchSpeciesDetails(detailsUrl: String) {
        viewModelScope.launch {

            when (val result = detailsRepo.getSpeciesDetails(detailsUrl)) {
                is Result.Success -> speciesDetails.value = result.data
                else -> TODO()
            }
        }
    }

    fun setData(detailsUrl: String?) {
        this.detailsUrl = detailsUrl
    }
}