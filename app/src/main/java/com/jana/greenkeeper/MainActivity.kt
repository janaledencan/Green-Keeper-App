package com.jana.greenkeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.auth.FirebaseAuth
import com.jana.greenkeeper.ui.theme.GreenKeeperTheme
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jana.greenkeeper.view.LoginScreen
import com.jana.greenkeeper.view.RegistrationScreen
import com.jana.greenkeeper.viewmodel.AuthenticationViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jana.greenkeeper.model.PlantRepository
import com.jana.greenkeeper.view.ApiPlantScreen
//import com.jana.greenkeeper.view.ApiPlantScreen
import com.jana.greenkeeper.view.EntryScreen
import com.jana.greenkeeper.view.MyPlantsScreen
import com.jana.greenkeeper.view.bottomsheet.createNotificationChannel
import com.jana.greenkeeper.viewmodel.ApiPlantViewModel
import com.jana.greenkeeper.viewmodel.NotificationViewModel
//import com.jana.greenkeeper.viewmodel.api.ApiPlantViewModelFactory
import com.jana.greenkeeper.viewmodel.api.RetrofitInstance

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

            GreenKeeperTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(authViewModel = authViewModel, navController = navController)
                }
            }

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
                    ApiPlantScreen(navController = navController)
                }
            }
        }
    }
}