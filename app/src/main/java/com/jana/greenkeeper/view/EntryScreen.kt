package com.jana.greenkeeper.view

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jana.greenkeeper.ui.theme.GreenKeeperTheme
import com.jana.greenkeeper.view.components.BackgroundImage
import com.jana.greenkeeper.viewmodel.AuthenticationViewModel

@Composable
fun EntryScreen(navController: NavController) {

    BackgroundImage(modifier = Modifier.alpha(1.0F))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(128.dp))
        Text(
            text = "Green Keeper",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                    color = Color.Green,
                    offset = Offset(5.0f, 10.0f),
                    blurRadius = 3f
                )
            )
        )

        Spacer(modifier = Modifier.height(46.dp))
        Button(
            onClick = {navController.navigate("login_screen")}
        ) { Text("Login") }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Don’t have an account?", fontSize = 14.sp)
        ClickableText(
            text = AnnotatedString(text="Register", spanStyle = SpanStyle(textDecoration = TextDecoration.Underline)),
            onClick = {navController.navigate("registration_screen")}
        )
        Spacer(modifier = Modifier.height(8.dp))


    }

}

@Preview(showBackground = true)
@Composable
fun EntryScreenPreview() {
    GreenKeeperTheme {
        EntryScreen(navController = NavController(LocalContext.current))
    }
}