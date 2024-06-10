package com.jana.greenkeeper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jana.greenkeeper.model.Plant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class PlantTrackerViewModel : ViewModel() {

    private val firestoreRepository = FirestorePlantRepository()
    private val emptyPlant = Plant(id = "", name = "", description = "", color = "Green")
    private val _currentPlantStream = MutableStateFlow(emptyPlant)
    val currentPlantStream: StateFlow<Plant> = _currentPlantStream

    val plantListStream: Flow<List<Plant>> = firestoreRepository.getAllPlants()

    fun addPlant(plant: Plant) = viewModelScope.launch {
        firestoreRepository.addPlant(plant)
    }

    fun updateCurrentPlant(plant: Plant) {
        _currentPlantStream.value = plant
    }

    fun resetCurrentPlant() {
        _currentPlantStream.value = emptyPlant
    }

    fun savePlant() = viewModelScope.launch {
        val currentPlant = _currentPlantStream.value
        if (currentPlant.id.isNotEmpty()) {
            firestoreRepository.updatePlant(currentPlant)
        } else {
            firestoreRepository.addPlant(currentPlant.copy(id = "")) // Reset id for new plant
        }
    }

    fun deletePlant(plant: Plant) = viewModelScope.launch {
        firestoreRepository.deletePlant(plant)
    }
}