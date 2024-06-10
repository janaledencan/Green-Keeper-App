package com.jana.greenkeeper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.jana.greenkeeper.viewmodel.AuthenticationViewModel

@Composable
fun MainScreen(authViewModel: AuthenticationViewModel, navController: NavHostController) {
    LaunchedEffect(authViewModel.isLoggedIn) {
        if (authViewModel.isLoggedIn) {
            navController.navigate("main_plants_screen") {
                popUpTo(0) { inclusive = true } // Clear the back stack
            }
        } else {
            navController.navigate("login_screen") {
                popUpTo(0) { inclusive = true } // Clear the back stack
            }
        }
    }
}