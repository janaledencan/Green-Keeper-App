package com.jana.greenkeeper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jana.greenkeeper.model.Plant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


class PlantTrackerViewModel(
    private val authViewModel: AuthenticationViewModel
) : ViewModel() {

    private val firestoreRepository = FirestorePlantRepository()
    private val emptyPlant = Plant(id = "", name = "", description = "", color = "Green")
    private val _currentPlantStream = MutableStateFlow(emptyPlant)
    val currentPlantStream: StateFlow<Plant> = _currentPlantStream

    val plantListStream: Flow<List<Plant>> = flow {
        authViewModel.currentUser?.let { user ->
            emitAll(firestoreRepository.getAllPlants(user.uid))
        }
    }

    fun addPlant(plant: Plant) = viewModelScope.launch {
        authViewModel.currentUser?.let { user ->
            firestoreRepository.addPlant(plant.copy(userId = user.uid))
        }
    }

    fun updateCurrentPlant(plant: Plant) {
        _currentPlantStream.value = plant
    }

    fun resetCurrentPlant() {
        _currentPlantStream.value = emptyPlant
    }

    fun savePlant() = viewModelScope.launch {
        val currentPlant = _currentPlantStream.value
        authViewModel.currentUser?.let { user ->
            if (currentPlant.id.isNotEmpty()) {
                firestoreRepository.updatePlant(currentPlant)
            } else {
                firestoreRepository.addPlant(currentPlant.copy(userId = user.uid))
            }
           // notificationViewModel.scheduleNotification(currentPlant)
        }
    }

    fun deletePlant(plant: Plant) = viewModelScope.launch {
        firestoreRepository.deletePlant(plant)
    }
}