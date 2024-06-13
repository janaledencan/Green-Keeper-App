package com.jana.greenkeeper.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DropdownSelectField(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }

    Column {
        /* Text(
             text = "Select Category:",
             style = MaterialTheme.typography.bodySmall,
             modifier = Modifier.padding(vertical = 8.dp)
         )
 */
        OutlinedButton(
            onClick = { expanded.value = true },
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            Text(text = if (selectedCategory.isEmpty()) "Select Category" else selectedCategory)
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(text = category)},
                    onClick = {
                        onCategorySelected(category)
                        expanded.value=false
                    }
                )

            }
        }
    }
}