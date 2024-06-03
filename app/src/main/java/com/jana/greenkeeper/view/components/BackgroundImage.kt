package com.jana.greenkeeper.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.jana.greenkeeper.R

@Composable
fun BackgroundImage(modifier: Modifier) {
    Box (modifier){
        Image(
            painter = painterResource(id = R.drawable.gk_background_img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }
}