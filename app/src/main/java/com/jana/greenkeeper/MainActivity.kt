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
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.jana.greenkeeper.view.LoginScreen
import com.jana.greenkeeper.view.RegistrationScreen
import com.jana.greenkeeper.viewmodel.AuthenticationViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jana.greenkeeper.view.ApiPlantScreen
import com.jana.greenkeeper.view.EntryScreen
import com.jana.greenkeeper.view.MyPlantsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            GreenKeeperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //val currentUser = FirebaseAuth.getInstance().currentUser
                    //LoginScreen(viewModel = AuthenticationViewModel(), context = context)
                    //RegistrationScreen(viewModel = AuthenticationViewModel(), context = context)
                }
            }
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "entry_screen") {
                composable("entry_screen") {
                    EntryScreen(
                        navController = navController
                    )
                }
                composable("login_screen") {
                    LoginScreen(
                        viewModel = AuthenticationViewModel(),
                        context = context,
                        navController = navController
                    )
                }
                composable("registration_screen") {
                    RegistrationScreen(
                        viewModel = AuthenticationViewModel(),
                        context = context,
                        navController = navController
                    )
                }
                composable("main_plants_screen") {
                    MyPlantsScreen(navController = navController)
                }
                composable("api_plant_screen") {
                    ApiPlantScreen(navController = navController)
                }

            }
        }
    }
}

