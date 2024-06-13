package com.jana.greenkeeper.view

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jana.greenkeeper.R
import com.jana.greenkeeper.view.components.BackgroundImage
import com.jana.greenkeeper.viewmodel.AuthenticationViewModel

@Composable
fun RegistrationScreen(viewModel: AuthenticationViewModel, context: Context, navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    BackgroundImage(modifier = Modifier.alpha(0.8F).fillMaxSize())
    Column(
        modifier = Modifier .fillMaxSize() .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(112.dp))
        Text(
            text = "Get Started",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "by creating a free account",
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(80.dp))
        Button(
            onClick = { viewModel.register(context, email, password) },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.custom_green))
        ) { Text("Register") }
        Spacer(modifier = Modifier.height(8.dp))
        ClickableText(text = AnnotatedString("Already a member? Log In"), onClick = {navController.navigate("login_screen")})
    }

}