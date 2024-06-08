package com.jana.greenkeeper.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jana.greenkeeper.ui.theme.GreenKeeperTheme

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

@Preview(showBackground = true)
@Composable
fun MyPlantsScreenPreview() {
    GreenKeeperTheme {
        MyPlantsScreen(navController = NavController(LocalContext.current)
        )
    }
}