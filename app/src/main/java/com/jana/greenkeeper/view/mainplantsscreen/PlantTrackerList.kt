/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

/*
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
        items(items = plants) { juice ->
            PlantTrackerListItem(juice, onDelete, onUpdate)
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
                Plant(1, "Mango", "Yummy!", "Yellow"),
                Plant(2, "Orange", "Best plant", "Orange"),
                Plant(3, "Grape", "Greenest of them all", "Magenta"),

            ),
            onDelete = {},
            onUpdate = {})
    }
}
*/