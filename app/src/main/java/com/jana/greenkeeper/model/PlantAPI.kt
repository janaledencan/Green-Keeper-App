package com.jana.greenkeeper.model

import com.google.gson.annotations.SerializedName

data class PlantAPI(
    @SerializedName("Categories") val categories: String,
    @SerializedName("Common name (fr.)") val commonNameFr: String? = null,
    @SerializedName("Img") val img: String,
    @SerializedName("Zone") val zone: List<String>,
    @SerializedName("Family") val family: String,
    @SerializedName("Common name") val commonName: List<String>? = null,
    @SerializedName("Latin name") val latinName: String,
    @SerializedName("Other names") val otherNames: String? = null,
    @SerializedName("Description") val description: String? = null,
    @SerializedName("Origin") val origin: List<String>? = null,
    @SerializedName("id") val id: String,
    @SerializedName("Climat") val climat: String
)
