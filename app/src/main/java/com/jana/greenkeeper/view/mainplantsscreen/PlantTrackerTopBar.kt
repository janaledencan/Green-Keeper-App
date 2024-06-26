package com.jana.greenkeeper.view.mainplantsscreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jana.greenkeeper.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantTrackerTopAppBar(isMain: Boolean, onNavigationClick: () -> Unit ,  onLogoutClick: () -> Unit ,modifier: Modifier = Modifier) {


   if(isMain) {
       CenterAlignedTopAppBar(
           modifier = modifier,
           colors = TopAppBarDefaults.largeTopAppBarColors(
               containerColor = MaterialTheme.colorScheme.background
           ),
           title = {
               Text(
                   text = stringResource(R.string.main_screen_title),
                   modifier = Modifier.padding(horizontal = 16.dp),
                   fontWeight = FontWeight.Bold,
                   style = MaterialTheme.typography.titleLarge,
                   color = MaterialTheme.colorScheme.onBackground
               )
           },
           navigationIcon = {
               IconButton(onClick = onLogoutClick) { // Add the logout button
                   Icon(
                       painter = painterResource(R.drawable.logout_24dp),
                       contentDescription = "Logout",
                   )
               }
           },
           actions = {
               IconButton(onClick = onNavigationClick) {
                   Icon(
                       imageVector = Icons.Filled.ArrowForward,
                       contentDescription = stringResource(R.string.api_screen)
                   )
               }
           }
       )
   }
    else {
       CenterAlignedTopAppBar(
           modifier = modifier,
           colors = TopAppBarDefaults.largeTopAppBarColors(
               containerColor = MaterialTheme.colorScheme.background
           ),
           title = {
               Text(
                   text = stringResource(R.string.api_screen_title),
                   modifier = Modifier.padding(horizontal = 16.dp),
                   fontWeight = FontWeight.Bold,
                   style = MaterialTheme.typography.titleLarge,
                   color = MaterialTheme.colorScheme.onBackground
               )
           },
           navigationIcon = {
               IconButton(onClick = onNavigationClick) {
                   Icon(
                       imageVector = Icons.Filled.ArrowBack,
                       contentDescription = "back"
                   )
               }
           }
       )
   }
}
