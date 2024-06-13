package com.jana.greenkeeper.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jana.greenkeeper.model.PlantAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import java.lang.reflect.Type



class ApiViewModel : ViewModel() {
    private val client = OkHttpClient()
    private val gson = Gson()

    private val _plantsList = MutableStateFlow<List<PlantAPI>>(emptyList())
    val plantsList: StateFlow<List<PlantAPI>> = _plantsList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getResult(category: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                withContext(Dispatchers.IO) {
                    val request = Request.Builder()
                        .url("https://house-plants2.p.rapidapi.com/category/$category")
                        .get()
                        .addHeader("x-rapidapi-key", "")
                        .addHeader("x-rapidapi-host", "house-plants2.p.rapidapi.com")
                        .build()

                    val response = client.newCall(request).execute()
                    val responseBody: ResponseBody? = response.body

                    if (response.isSuccessful && responseBody != null) {
                        val responseString = responseBody.string()
                        val listType: Type = object : TypeToken<List<PlantAPI>>() {}.type
                        val plants: List<PlantAPI> = gson.fromJson(responseString, listType)
                        _plantsList.value = plants
                    } else {
                        _error.value = "Error: ${response.message}"
                    }
                }
            } catch (e: Exception) {
                _error.value = "Exception: ${e.message}"
                Log.e("ApiViewModel", "Error fetching plants", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}