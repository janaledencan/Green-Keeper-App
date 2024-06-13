package com.jana.greenkeeper.view


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jana.greenkeeper.view.bottomsheet.EntryBottomSheet
import com.jana.greenkeeper.view.mainplantsscreen.PlantTrackerFAB
import com.jana.greenkeeper.view.mainplantsscreen.PlantTrackerList
import com.jana.greenkeeper.view.mainplantsscreen.PlantTrackerTopAppBar
import com.jana.greenkeeper.viewmodel.AuthenticationViewModel
import com.jana.greenkeeper.viewmodel.NotificationViewModel
import com.jana.greenkeeper.viewmodel.PlantTrackerViewModel
import kotlinx.coroutines.launch
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.jana.greenkeeper.view.bottomsheet.NotificationBottomSheet
import com.jana.greenkeeper.view.mainplantsscreen.NotificationLeftFAB


@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlantsScreen(
    navController: NavController,
    context:Context,
    authViewModel: AuthenticationViewModel,
    modifier: Modifier = Modifier,
) {
    val notificationViewModel = NotificationViewModel()
    val plantTrackerViewModel = PlantTrackerViewModel(authViewModel)

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )

    val leftBottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )

    val scope = rememberCoroutineScope()
    val trackerState by plantTrackerViewModel.plantListStream.collectAsState(emptyList())

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
    ) {
        Scaffold(
            topBar = {
                PlantTrackerTopAppBar(
                    isMain = true,
                    onNavigationClick = { navController.navigate("api_plant_screen") },
                    onLogoutClick = {
                        authViewModel.logout()
                        navController.navigate("login_screen") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    }
                )
            },
            floatingActionButton = {
                Row( modifier = Modifier.padding(start = 24.dp)) {
                    Box(modifier = Modifier.fillMaxWidth()) {

                        Spacer(modifier = Modifier.width(24.dp))
                        NotificationLeftFAB(
                            onClick = {
                                scope.launch { leftBottomSheetScaffoldState.bottomSheetState.expand() }
                            },
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        )

                        PlantTrackerFAB(
                            onClick = {
                                plantTrackerViewModel.resetCurrentPlant()
                                scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                            },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp)
                        )
                    }
                }

                /*Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                    ) {

                    NotificationLeftFAB(
                        onClick = {
                            scope.launch { leftBottomSheetScaffoldState.bottomSheetState.expand() }
                        }
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    PlantTrackerFAB(
                        onClick = {
                            plantTrackerViewModel.resetCurrentPlant()
                            scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                        }
                    )
                }*/
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

    NotificationBottomSheet(
        sheetScaffoldState = leftBottomSheetScaffoldState,
        onCancel = {
            scope.launch {
                leftBottomSheetScaffoldState.bottomSheetState.hide()
            }
        },
        onSubmit = { title, message, timeInMillis ->
            notificationViewModel.scheduleNotification(
                context = context,
                title = title,
                message = message,
                timeInMillis = timeInMillis
            )
            scope.launch {
                leftBottomSheetScaffoldState.bottomSheetState.hide()
            }
        }
    )
}


/*
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlantsScreen(
    navController: NavController,
    authViewModel: AuthenticationViewModel, 
    modifier: Modifier = Modifier,
) {

    val plantTrackerViewModel = PlantTrackerViewModel(authViewModel)

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )

    val scope = rememberCoroutineScope()
    val trackerState by plantTrackerViewModel.plantListStream.collectAsState(emptyList())

    var showLeftBottomSheet by remember { mutableStateOf(false) }
    var showRightBottomSheet by remember { mutableStateOf(false) }



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
        },
        notificationViewModel = NotificationViewModel()
    ) {
        Scaffold(
            topBar = {
                PlantTrackerTopAppBar(
                    isMain = true,
                    onNavigationClick = { navController.navigate("api_plant_screen") },
                    onLogoutClick = {
                        authViewModel.logout()
                        navController.navigate("login_screen") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    }
                )
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
}*/

/* OVAJ JE BIO TOP
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlantsScreen(
    navController: NavController,
    authViewModel: AuthenticationViewModel,
    modifier: Modifier = Modifier,
) {
    val notificationViewModel = NotificationViewModel(authViewModel)
    val plantTrackerViewModel = PlantTrackerViewModel(authViewModel)

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )

    val leftBottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )

    val scope = rememberCoroutineScope()
    val trackerState by plantTrackerViewModel.plantListStream.collectAsState(emptyList())

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
    ) {
        Scaffold(
            topBar = {
                PlantTrackerTopAppBar(
                    isMain = true,
                    onNavigationClick = { navController.navigate("api_plant_screen") },
                    onLogoutClick = {
                        authViewModel.logout()
                        navController.navigate("login_screen") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    }
                )
            },
            floatingActionButton = {
                Column {
                    PlantTrackerFAB(
                        onClick = {
                            plantTrackerViewModel.resetCurrentPlant()
                            scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    NotificationLeftFAB(
                        onClick = {
                            scope.launch { leftBottomSheetScaffoldState.bottomSheetState.expand() }
                        }
                    )
                }
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

    NotificationBottomSheet(
        sheetScaffoldState = leftBottomSheetScaffoldState,
        onCancel = {
            scope.launch {
                leftBottomSheetScaffoldState.bottomSheetState.hide()
            }
        },
        onSubmit = {
            // Add your notification logic here
            scope.launch {
                leftBottomSheetScaffoldState.bottomSheetState.hide()
            }
        }
    )
}
*/

/* TAJ JE SAD RADIO U 19h
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlantsScreen(
    navController: NavController,
    authViewModel: AuthenticationViewModel,
    modifier: Modifier = Modifier,
) {
    val notificationViewModel = NotificationViewModel(authViewModel)
    val plantTrackerViewModel = PlantTrackerViewModel(authViewModel)

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )

    val leftBottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )

    val scope = rememberCoroutineScope()
    val trackerState by plantTrackerViewModel.plantListStream.collectAsState(emptyList())

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
    ) {
        Scaffold(
            topBar = {
                PlantTrackerTopAppBar(
                    isMain = true,
                    onNavigationClick = { navController.navigate("api_plant_screen") },
                    onLogoutClick = {
                        authViewModel.logout()
                        navController.navigate("login_screen") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    }
                )
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



        NotificationBottomSheet(
            sheetScaffoldState = leftBottomSheetScaffoldState ,
            onCancel = {
                scope.launch {
                    leftBottomSheetScaffoldState.bottomSheetState.hide()
                }
            },
            onSubmit = {
                //iz VIEWMODELA metodu za notifikaciju
                scope.launch {
                    leftBottomSheetScaffoldState.bottomSheetState.hide()
                }
            }
        ) {
            Scaffold(
                topBar = {
                    PlantTrackerTopAppBar(
                        isMain = true,
                        onNavigationClick = { navController.navigate("api_plant_screen") },
                        onLogoutClick = {
                            authViewModel.logout()
                            navController.navigate("login_screen") {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                },


                floatingActionButton = {
                    NotificationLeftFAB(
                        onClick = {
                            scope.launch { leftBottomSheetScaffoldState.bottomSheetState.expand() }
                        }
                    )
                }
            ) {paddingValues ->
                Text(text = "Notification", modifier=Modifier.padding((paddingValues)))
            }
        }
    }
}
*/


/* OVAJ JE  KAO ZA TAJ DODATNI FAB
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlantsScreen(
    navController: NavController,
    authViewModel: AuthenticationViewModel,
    modifier: Modifier = Modifier,
) {
    val plantTrackerViewModel = PlantTrackerViewModel(authViewModel)
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )
    val scope = rememberCoroutineScope()
    val trackerState by plantTrackerViewModel.plantListStream.collectAsState(emptyList())

    var showLeftBottomSheet by remember { mutableStateOf(false) }
    var showRightBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            PlantTrackerTopAppBar(
                isMain = true,
                onNavigationClick = { navController.navigate("api_plant_screen") },
                onLogoutClick = {
                    authViewModel.logout()
                    navController.navigate("login_screen") {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(16.dp)
            ) {
                FloatingActionButton(
                    onClick = { showLeftBottomSheet = true },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
                FloatingActionButton(
                    onClick = {
                        plantTrackerViewModel.resetCurrentPlant()
                        scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                }
            }
        }
    ) { contentPadding ->
        Column(Modifier.padding(contentPadding)) {
            PlantTrackerList(
                plants = trackerState,
                onDelete = { plant -> plantTrackerViewModel.deletePlant(plant) },
                onUpdate = { plant ->
                    plantTrackerViewModel.updateCurrentPlant(plant)
                    scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                },
            )
        }
    }

    if (showLeftBottomSheet) {
        NotificationBottomSheet(

        )
    }

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
        },
        notificationViewModel = NotificationViewModel()
    ){}
}
*/
/*
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MyPlantsScreenPreview() {
    GreenKeeperTheme {
        MyPlantsScreen(navController = NavController(LocalContext.current), authViewModel= AuthenticationViewModel(
            LocalContext.current),
        )
    }
}*/