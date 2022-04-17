package com.drunken.valostats.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drunken.valostats.models.Agent
import com.drunken.valostats.models.Bundle
import com.drunken.valostats.models.Map
import com.drunken.valostats.models.Spray
import com.drunken.valostats.network.ValorantApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomepageViewModel: ViewModel() {

    private val _allAgents = MutableLiveData<List<Agent>>()
    val allAgent: LiveData<List<Agent>>
        get() = _allAgents

    private val _allMaps = MutableLiveData<List<Map>>()
    val allMaps: LiveData<List<Map>>
        get() = _allMaps

    private val _allBundles = MutableLiveData<List<Bundle>>()
    val allBundles: LiveData<List<Bundle>>
        get() = _allBundles

    private val _allSprays = MutableLiveData<List<Spray>>()
    val allSpray: LiveData<List<Spray>>
        get() = _allSprays


    fun fetchAllAgents() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ValorantApi.retrofitService.getAllAgents()

            if (response.status == 200) {
                withContext(Dispatchers.Main) {
                    _allAgents.value = response.data!!
                }
            }
        }
    }

    fun fetchAllMaps() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ValorantApi.retrofitService.getAllMaps()

            if (response.status == 200) {
                withContext(Dispatchers.Main) {
                    _allMaps.value = response.data!!
                }
            }
        }
    }

    fun fetchAllBundles() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ValorantApi.retrofitService.getAllBundles()

            if (response.status == 200) {
                withContext(Dispatchers.Main) {
                    _allBundles.value = response.data!!
                }
            }
        }
    }

    fun fetchAllSprays() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ValorantApi.retrofitService.getAllSprays()

            if (response.status == 200) {
                withContext(Dispatchers.Main) {
                    _allSprays.value = response.data!!
                }
            }
        }
    }
}