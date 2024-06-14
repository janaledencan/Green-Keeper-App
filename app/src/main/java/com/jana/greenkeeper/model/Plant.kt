package com.jana.greenkeeper.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.jana.greenkeeper.R


import com.jana.greenkeeper.ui.theme.Orange as OrangeColor


data class Plant(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val color: String = "Green",
    val userId: String = "",
)


enum class PlantColor(val color: Color, @StringRes val label: Int) {
    Red(Color.Red, R.string.red),
    Blue(Color.Blue, R.string.blue),
    Green(Color.Green, R.string.green),
    Cyan(Color.Cyan, R.string.cyan),
    Yellow(Color.Yellow, R.string.yellow),
    Magenta(Color.Magenta, R.string.magenta),
    Orange(OrangeColor, R.string.orange)
}
