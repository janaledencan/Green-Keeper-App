package com.jana.greenkeeper.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.jana.greenkeeper.ui.theme.GreenKeeperTheme
import com.jana.greenkeeper.view.bottomsheet.EntryBottomSheet
import com.jana.greenkeeper.view.mainplantsscreen.PlantTrackerFAB
import com.jana.greenkeeper.view.mainplantsscreen.PlantTrackerList
import com.jana.greenkeeper.view.mainplantsscreen.PlantTrackerTopAppBar
import com.jana.greenkeeper.viewmodel.PlantTrackerViewModel
import kotlinx.coroutines.launch

/*
@Composable
fun MyPlantsScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "My Plants")
        Spacer(modifier = Modifier.height(10.dp))
        ClickableText(text = AnnotatedString("Go to Api Screen"), onClick = {navController.navigate("api_plant_screen")})

    }
}
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlantsScreen(navController: NavController,
    modifier: Modifier = Modifier,
) {

    val  plantTrackerViewModel = PlantTrackerViewModel()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )

    val scope = rememberCoroutineScope()
    val trackerState by plantTrackerViewModel.plantListStream.collectAsState(emptyList())

    Spacer(modifier = Modifier.height(10.dp))
    ClickableText(text = AnnotatedString("Go to Api Screen"), onClick = {navController.navigate("api_plant_screen")})

    EntryBottomSheet(
        plantTrackerViewModel = plantTrackerViewModel,
        sheetScaffoldState = bottomSheetScaffoldState,
        modifier = modifier,
        onCancel = {
            scope.launch {
                bottomSheetScaffoldState.bottomSheetState.hide()
            }
        },
        onSubmit = {
            plantTrackerViewModel.savePlant()
            scope.launch {
                bottomSheetScaffoldState.bottomSheetState.hide()
            }
        }
    )
    {
        Scaffold(
            topBar = {
                PlantTrackerTopAppBar()
            },
            floatingActionButton = {
                PlantTrackerFAB(
                    onClick = {
                        plantTrackerViewModel.resetCurrentPlant()
                        scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                    }
                )
            }
        ) { contentPadding ->
            Column(Modifier.padding(contentPadding)) {
                PlantTrackerList(
                    plants = trackerState,
                    onDelete = { plant -> plantTrackerViewModel.deletePlant(plant) },
                    onUpdate = { plant ->
                        plantTrackerViewModel.updateCurrentPlant(plant)
                        scope.launch {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                    },
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MyPlantsScreenPreview() {
    GreenKeeperTheme {
        MyPlantsScreen(navController = NavController(LocalContext.current)
        )
    }
}