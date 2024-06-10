package com.jana.greenkeeper.viewmodel

import com.google.firebase.firestore.FirebaseFirestore
import com.jana.greenkeeper.model.Plant
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


/*
class FirestorePlantRepository {

    private val db = FirebaseFirestore.getInstance()

    fun getAllPlants(): Flow<List<Plant>> = callbackFlow {
        val listener = db.collection("myPlants")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    close(e)
                    return@addSnapshotListener
                }

                val plants = snapshot?.toObjects(Plant::class.java) ?: emptyList()
                trySend(plants)
            }
        //korutine
        awaitClose { listener.remove() }
    }

    suspend fun addPlant(plant: Plant) {
        db.collection("myPlants").add(plant)
    }

    suspend fun updatePlant(plant: Plant) {
        db.collection("myPlants").document(plant.id.toString()).set(plant)
    }

    suspend fun deletePlant(plant: Plant) {
        db.collection("myPlants").document(plant.id.toString()).delete()
    }
}
*/
class FirestorePlantRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val plantCollection = firestore.collection("myPlants")

    fun getAllPlants(): Flow<List<Plant>> = callbackFlow {
        val listener = plantCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }

            val plants = snapshot?.documents?.mapNotNull { it.toObject(Plant::class.java) } ?: emptyList()
            trySend(plants)
        }
        awaitClose { listener.remove() }
    }

    suspend fun addPlant(plant: Plant) {
        val newPlantRef = plantCollection.document()
        val newPlant = plant.copy(id = newPlantRef.id.hashCode()) // Hash the document ID for consistency
        newPlantRef.set(newPlant).await()
    }

    suspend fun updatePlant(plant: Plant) {
        plantCollection.document(plant.id.toString()).set(plant).await()
    }

    suspend fun deletePlant(plant: Plant) {
        plantCollection.document(plant.id.toString()).delete().await()
    }
}