package com.jana.greenkeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jana.greenkeeper.ui.theme.GreenKeeperTheme
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import com.jana.greenkeeper.view.LoginScreen
import com.jana.greenkeeper.view.RegistrationScreen
import com.jana.greenkeeper.viewmodel.AuthenticationViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jana.greenkeeper.view.ApiPlantScreen
import com.jana.greenkeeper.view.EntryScreen
import com.jana.greenkeeper.view.MyPlantsScreen
import com.jana.greenkeeper.viewmodel.ApiViewModel
import com.jana.greenkeeper.viewmodel.NotificationViewModel


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()
            val authViewModel = AuthenticationViewModel(context = context)
            val notificationViewModel = NotificationViewModel()
            notificationViewModel.createNotificationChannel(context = context)
            val apiViewModel = ApiViewModel()

            GreenKeeperTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(authViewModel = authViewModel, navController = navController)

                    NavHost(navController = navController, startDestination = "entry_screen") {
                        composable("entry_screen") {
                            EntryScreen(
                                navController = navController
                            )
                        }
                        composable("login_screen") {
                            LoginScreen(
                                viewModel = authViewModel, // Use the shared AuthenticationViewModel
                                context = context,
                                navController = navController
                            )
                        }
                        composable("registration_screen") {
                            RegistrationScreen(
                                viewModel = authViewModel, // Use the shared AuthenticationViewModel
                                context = context,
                                navController = navController
                            )
                        }
                        composable("main_plants_screen") {
                            MyPlantsScreen(navController = navController, authViewModel = authViewModel, context = LocalContext.current)
                        }
                        composable("api_plant_screen") {
                            ApiPlantScreen(apiViewModel = apiViewModel ,navController = navController)
                        }
                    }
                }
            }


        }
    }
}