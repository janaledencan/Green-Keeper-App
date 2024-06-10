package com.jana.greenkeeper.viewmodel

import com.google.firebase.firestore.FirebaseFirestore
import com.jana.greenkeeper.model.Plant
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


class FirestorePlantRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val plantCollection = firestore.collection("myPlants")

    fun getAllPlants(): Flow<List<Plant>> = callbackFlow {
        val listener = plantCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }

            val plants = snapshot?.documents?.mapNotNull {
                it.toObject(Plant::class.java)?.copy(id = it.id) // Ensure id is set to the document ID
            } ?: emptyList()
            trySend(plants)
        }
        awaitClose { listener.remove() }
    }

    suspend fun addPlant(plant: Plant) {
        val newPlantRef = plantCollection.document()
        val newPlant = plant.copy(id = newPlantRef.id)
        newPlantRef.set(newPlant).await()
    }

    suspend fun updatePlant(plant: Plant) {
        plantCollection.document(plant.id).set(plant).await()
    }

    suspend fun deletePlant(plant: Plant) {
        plantCollection.document(plant.id).delete().await()
    }
}