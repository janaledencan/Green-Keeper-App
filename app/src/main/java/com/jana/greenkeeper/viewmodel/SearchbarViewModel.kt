package com.jana.greenkeeper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jana.greenkeeper.model.PlantAPI
import com.jana.greenkeeper.viewmodel.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/*
class SearchbarViewModel : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    private val _plantsList = MutableStateFlow<List<PlantAPI>>(emptyList())
    val plantsList: StateFlow<List<PlantAPI>> = _plantsList

    val plantsList = searchText
        .combine(_plantsList) { text, plants ->//combine searchText with _plantsList
            if (text.isBlank()) { //return the entery list of plants if not is typed
                plants
            }
            categories.filter { category ->// filter and return a list of plants based on the text the user typed
                category.uppercase().contains(text.trim().uppercase())
            }
        }.stateIn(//basically convert the Flow returned from combine operator to StateFlow
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),//it will allow the StateFlow survive 5 seconds before it been canceled
            initialValue = _plantsList.value
        )

    fun onSearchTextChange(newText: String) {
        _searchText.value = newText
    }

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (_isSearching.value) {
            searchPlants(_searchText.value)
        }
    }

    private fun searchPlants(category: String) = viewModelScope.launch {
        try {
            val response = RetrofitInstance.api.getPlantsByCategory(category)
            _plantsList.value = response
            print(_plantsList)
        } catch (e: Exception) {
            // Handle the error
        }
    }
}
*/


/*
class SearchbarViewModel : ViewModel() {   //MainViewModel
    //first state whether the search is happening or not
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    //second state the text typed by the user
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    //third state the list to be filtered
    private val _plantsList = MutableStateFlow(plants)
    val plantsList = searchText
        .combine(_plantsList) { text, plants ->//combine searchText with _plantsList
            if (text.isBlank()) { //return the entery list of plants if not is typed
                plants
            }
            plants.filter { plant ->// filter and return a list of plants based on the text the user typed
                plant.uppercase().contains(text.trim().uppercase())
            }
        }.stateIn(//basically convert the Flow returned from combine operator to StateFlow
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),//it will allow the StateFlow survive 5 seconds before it been canceled
            initialValue = _plantsList.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }
}
*/

/*
private val plants = listOf(
    "Ficus",
    "Bamboo",
    "Cactus",
    "Orchid",
    "Split-Leaf Philodendron"
    )
    */


class SearchbarViewModel : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    private val _plantsList = MutableStateFlow<List<PlantAPI>>(emptyList())
    val plantsList: StateFlow<List<PlantAPI>> = _plantsList

    fun onSearchTextChange(newText: String) {
        _searchText.value = newText
    }

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (_isSearching.value) {
            searchPlants(_searchText.value)
        }
    }

    private fun searchPlants(category: String) = viewModelScope.launch {
        try {
            val response = RetrofitInstance.api.getPlantsByCategory(category)
            _plantsList.value = response
        } catch (e: Exception) {
            // Handle the error
        }
    }
}


 private val categories = listOf(
    "Dracaena",
    "Palm",
    "Anthurium",
    "Other",
    "Aglaonema",
    "Hanging",
    "Bromeliad",
    "Spathiphyllum",
    "Flower"
 )