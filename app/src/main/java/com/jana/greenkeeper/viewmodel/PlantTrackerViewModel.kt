package com.jana.greenkeeper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jana.greenkeeper.model.Plant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/*class PlantTrackerViewModel {

    val db = FirebaseFirestore.getInstance()
    private val emptyPlant = Plant(0, "", "", PlantColor.Red.name)
    private val _currentPlantStream = MutableStateFlow(emptyPlant)
    val currentPlantStream: StateFlow<Plant> = _currentPlantStream
    val plantStream: Flow<List<Plant>> = TODO() //RIJEŠITI DRUGACIJE
    val plantListStream: Flow<List<Plant>> = plantStream


    fun addPlant(plant: Plant){
        // Add a new document with a generated ID
        db.collection("myPlants")
            .add(plant)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }


    fun resetCurrentPlant() = _currentPlantStream.update { emptyPlant }
    fun updateCurrentPlant(plant: Plant) {

        db.collection("myPlants").document()
    }
   /* fun writeNewUser(userId: String, name: String, email: String) {
        val user = User(name, email)

        database.child("users").child(userId).setValue(user)
    }
    +7
    */
    fun savePlant() = viewModelScope.launch {
        if (_currentPlantStream.value.id > 0) {
            plantRepository.updatePlant(_currentPlantStream.value)
        } else {
            plantRepository.addPlant(_currentPlantStream.value)
        }
    }

    fun deletePlant(plant: Plant) = viewModelScope.launch {
        plantRepository.deletePlant(plant)
    }

}
*/

class PlantTrackerViewModel : ViewModel() {

    private val firestoreRepository = FirestorePlantRepository()
    private val emptyPlant = Plant(id = 0, name = "", description = "", color = "Green")
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
        if (currentPlant.id > 0) {
            firestoreRepository.updatePlant(currentPlant)
        } else {
            //firestoreRepository.addPlant(currentPlant)
            firestoreRepository.addPlant(currentPlant.copy(id = 0)) // Ensure id is reset before adding
        }
    }

    fun deletePlant(plant: Plant) = viewModelScope.launch {
        firestoreRepository.deletePlant(plant)
    }
}

