package com.jana.greenkeeper.view.mainplantsscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jana.greenkeeper.R
import com.jana.greenkeeper.model.Plant


@Composable
fun PlantTrackerList(
    plants: List<Plant>,
    onDelete: (Plant) -> Unit,
    onUpdate: (Plant) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 8.dp),
    ) {
        items(items = plants) { plant ->
            PlantTrackerListItem(plant, onDelete, onUpdate)
        }
    }
}

@Composable
fun PlantTrackerListItem(
    plant: Plant,
    onDelete: (Plant) -> Unit,
    onUpdate: (Plant) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onUpdate(plant)
            }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PottedPlantIcon(plant.color)
        PlantDetails(plant, modifier.weight(1f))
        DeleteButton(
            onDelete = {
                onDelete(plant)
            },
            modifier = Modifier.align(Alignment.Top)
        )
    }
}

@Composable
fun PlantDetails(plant: Plant, modifier: Modifier = Modifier) {
    Column(modifier.padding(start = 16.dp), verticalArrangement = Arrangement.Top) {
        Text(
            text = plant.name,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        )
        Text(plant.description)

    }
}

@Composable
fun DeleteButton(onDelete: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = { onDelete() },
    ) {
        Icon(
            modifier = modifier,
            painter = painterResource(R.drawable.delete_icon),
            contentDescription = stringResource(R.string.delete)
        )
    }
}

@Preview
@Composable
fun JuiceTrackerListPreview() {
    MaterialTheme {
        PlantTrackerList(
            plants = listOf(
                Plant("1", "Mango", "Yummy!", "Yellow"),
                Plant("2", "Orange", "Best plant", "Orange"),
                Plant("3", "Grape", "Greenest of them all", "Magenta"),

            ),
            onDelete = {},
            onUpdate = {})
    }
}
