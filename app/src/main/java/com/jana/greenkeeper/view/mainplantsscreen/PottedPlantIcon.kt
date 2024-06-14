package com.jana.greenkeeper.view.mainplantsscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.jana.greenkeeper.R
import com.jana.greenkeeper.model.PlantColor


@Composable
fun PottedPlantIcon(color: String, modifier: Modifier = Modifier) {
    val plantIconContentDescription = stringResource(R.string.plant_color, color)
    Box(
        modifier.semantics {
            contentDescription = plantIconContentDescription
        }
    ) {
        Icon(
            painter = painterResource(R.drawable.potted_plant_24),
            contentDescription = null,
            tint = PlantColor.valueOf(color).color,
            modifier = Modifier.align(Alignment.Center)
        )

    }
}
