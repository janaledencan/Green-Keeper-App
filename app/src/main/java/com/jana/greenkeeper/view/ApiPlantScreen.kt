package com.jana.greenkeeper.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jana.greenkeeper.ui.theme.GreenKeeperTheme
import com.jana.greenkeeper.view.mainplantsscreen.PlantTrackerTopAppBar
import com.jana.greenkeeper.viewmodel.SearchbarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiPlantScreen(navController: NavController) {

    val viewModel = SearchbarViewModel()

    // Collecting states from ViewModel
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val plantsList by viewModel.plantsList.collectAsState()

    Scaffold(
        topBar = {
            PlantTrackerTopAppBar(isMain = false, onNavigationClick = { navController.navigate("main_plants_screen") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            SearchBar(
                query = searchText, // text showed on SearchBar
                onQueryChange = viewModel::onSearchTextChange, // update the value of searchText
                onSearch = { /* Perform search or additional action if needed */ },
                active = isSearching, // whether the user is searching or not
                onActiveChange = { viewModel.onToogleSearch() }, // callback when active state changes
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                LazyColumn {
                    items(plantsList) { plant ->
                        Text(
                            text = plant,
                            modifier = Modifier.padding(
                                start = 8.dp,
                                top = 4.dp,
                                end = 8.dp,
                                bottom = 4.dp
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .size(width = 240.dp, height = 100.dp)
            ) {
                Text(
                    text = "Elevated",
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ApiPlantScreenPreview() {
    GreenKeeperTheme {
        ApiPlantScreen(  navController = NavController(LocalContext.current)
        )
    }
}
