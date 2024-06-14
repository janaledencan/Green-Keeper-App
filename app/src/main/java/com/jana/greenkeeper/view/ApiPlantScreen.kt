package com.jana.greenkeeper.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jana.greenkeeper.R
import com.jana.greenkeeper.view.components.DropdownSelectField
import com.jana.greenkeeper.view.mainplantsscreen.PlantTrackerTopAppBar
import com.jana.greenkeeper.viewmodel.ApiViewModel

@Composable
fun ApiPlantScreen(apiViewModel: ApiViewModel, navController: NavController) {
    val plantsResponse by apiViewModel.plantsList.collectAsState()
    val isLoading by apiViewModel.isLoading.collectAsState()
    val error by apiViewModel.error.collectAsState()
    val categories = listOf("Dracaena", "Palm", "Anthurium", "Aglaonema", "Hanging", "Bromeliad", "Spathiphyllum", "Flower", "Aralia", "Ficus", "Sansevieria", "Foliage plant", "Dieffenbachia", "Philodendron",  "Cactus & Succulent",  "Schefflera",  "Topiairy",  "Grass", "Fern",  "Ground Cover","Other")
    val (selectedCategory, setSelectedCategory) = remember { mutableStateOf("") }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                PlantTrackerTopAppBar(isMain = false, onNavigationClick = { navController.navigate("main_plants_screen")},
                    onLogoutClick = {})
            },

            ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    DropdownSelectField(
                        categories = categories,
                        selectedCategory = selectedCategory,
                        onCategorySelected = { category ->
                            setSelectedCategory(category)
                        }
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Button(
                        onClick = {
                            apiViewModel.getResult(selectedCategory)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.custom_green))
                    ) {
                        Text("Search")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.padding(end = 16.dp))
                    }

                    if (!error.isNullOrEmpty()) {
                        Text(text = error ?: "", color = Color.Red)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn (modifier = Modifier.align(Alignment.CenterHorizontally)){
                    items(plantsResponse) { plant ->
                        ElevatedCard(
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                            ),
                            modifier = Modifier
                                .padding(horizontal = 20.dp) // Adds horizontal padding (left and right)
                                .padding(vertical = 16.dp)

                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp).fillMaxWidth(0.9f)
                            ) {
                                Text(
                                    text = plant.commonNameFr ?: "Unknown French Name",
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )
                                plant.img.takeIf { it.isNotBlank() }?.let { imageUrl ->

                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(plant.img)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = "Plant image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxWidth().height(200.dp),
                                        onLoading = {
                                            Log.d("ImageLoading", "Loading image from URL: $imageUrl")
                                        },
                                        onError = {
                                            Log.e("ImageLoading", "Error loading image from URL: $imageUrl")
                                        }

                                    )
                                }
                                Text(
                                    text = "Latin name: ${plant.latinName}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(top = 16.dp)
                                )
                                Text(
                                    text = "Family: ${plant.family}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Text(
                                    text = "Climate: ${plant.climat}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}