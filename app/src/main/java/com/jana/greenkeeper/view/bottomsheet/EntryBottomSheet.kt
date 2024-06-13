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
package com.jana.greenkeeper.view.bottomsheet

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.jana.greenkeeper.R
import com.jana.greenkeeper.model.Plant
import com.jana.greenkeeper.model.PlantColor
import com.jana.greenkeeper.viewmodel.PlantTrackerViewModel
//R, .data.Plant, .data.PlantColor, .PlantTrackerViewModel
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context
import java.util.Locale


private fun findColorIndex(color: String): Int {
    val plantColor = PlantColor.valueOf(color)
    return PlantColor.values().indexOf(plantColor)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryBottomSheet(
    plantTrackerViewModel: PlantTrackerViewModel,
    //notificationViewModel: NotificationViewModel,
    sheetScaffoldState: BottomSheetScaffoldState,
    onCancel: () -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val plant by plantTrackerViewModel.currentPlantStream.collectAsState()

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = sheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column {
                SheetHeader()
                SheetForm(
                    plant = plant,
                    onUpdatePlant = plantTrackerViewModel::updateCurrentPlant,
                    onCancel = onCancel,
                    onSubmit = { //plantName, notificationTime ->
                        /*notificationViewModel.scheduleNotification(
                            applicationContext = LocalContext.current,
                            title = "Plant Reminder",
                            message = "It's time to water your $plantName",
                            timeInMillis = notificationTime
                        )*/
                        onSubmit()
                    }
                )
            }
        }
    ) {
        content()
    }
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryBottomSheet(
    plantTrackerViewModel: PlantTrackerViewModel,
    notificationViewModel: NotificationViewModel,
    sheetScaffoldState: BottomSheetScaffoldState,
    onCancel: () -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val plant by plantTrackerViewModel.currentPlantStream.collectAsState()

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = sheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column {
                SheetHeader()
                SheetForm(
                    plant = plant,
                    onUpdatePlant = plantTrackerViewModel::updateCurrentPlant,
                    onCancel = onCancel,
                    onSubmit = { plantName, notificationTime ->
                        notificationViewModel.scheduleNotification(
                            applicationContext = LocalContext.current,
                            title = "Plant Reminder",
                            message = "It's time to water your $plantName",
                            timeInMillis = notificationTime
                        )
                        onSubmit()
                    }
                )
            }
        }
    ) {
        content()
    }
}*/

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryBottomSheet(
    plantTrackerViewModel: PlantTrackerViewModel,
    notificationViewModel: NotificationViewModel,
    sheetScaffoldState: BottomSheetScaffoldState,
    onCancel: () -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val plant by plantTrackerViewModel.currentPlantStream.collectAsState()

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = sheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column {
                SheetHeader()
                SheetForm(
                    plant = plant,
                    onUpdatePlant = plantTrackerViewModel::updateCurrentPlant,
                    onCancel = onCancel,
                    onSubmit = { plantName, notificationTime ->
                        notificationViewModel.scheduleNotification(
                            applicationContext = LocalContext.current,
                            title = "Plant Reminder",
                            message = "It's time to water your $plantName",
                            timeInMillis = notificationTime
                        )
                        onSubmit()
                    }
                )
            }
        }
    ) {
        content()
    }
}
*/

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryBottomSheet(
    plantTrackerViewModel: PlantTrackerViewModel,
    notificationViewModel: NotificationViewModel,
    sheetScaffoldState: BottomSheetScaffoldState,
    onCancel: () -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val plant by plantTrackerViewModel.currentPlantStream.collectAsState()

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = sheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column {
                SheetHeader()
                SheetForm(
                    plant = plant,
                    onUpdatePlant = plantTrackerViewModel::updateCurrentPlant,
                    onCancel = onCancel,
                    onSubmit = { plantName, notificationTime, context ->
                        notificationViewModel.scheduleNotification(
                            applicationContext = context,
                            title = "Plant Reminder",
                            message = "It's time to water your $plantName",
                            timeInMillis = notificationTime
                        )
                        onSubmit()
                    }
                )
            }
        }
    ) {
        content()
    }
}
*/


/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryBottomSheet(
    plantTrackerViewModel: PlantTrackerViewModel,
    notificationViewModel: NotificationViewModel,
    sheetScaffoldState: BottomSheetScaffoldState,
    onCancel: () -> Unit,
    onSubmit: (String, Long) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val plant by plantTrackerViewModel.currentPlantStream.collectAsState()

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = sheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column {
                SheetHeader()
                SheetForm(
                    plant = plant,
                    onUpdatePlant = plantTrackerViewModel::updateCurrentPlant,
                    onCancel = onCancel,
                    onSubmit = { plantName, notificationTime ->
                        notificationViewModel.scheduleNotification(
                            applicationContext =  LocalContext.current,
                            title = "Plant Reminder",
                            message = "It's time to water your $plantName",
                            timeInMillis = notificationTime
                        )
                        onSubmit()
                    }
                )
            }
        }
    ) {
        content()
    }
}*/


/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryBottomSheet(
    plantTrackerViewModel: PlantTrackerViewModel,
    sheetScaffoldState: BottomSheetScaffoldState,
    onCancel: () -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val plant by plantTrackerViewModel.currentPlantStream.collectAsState()

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = sheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column {
                SheetHeader()
                SheetForm(
                    plant = plant,
                    onUpdatePlant = plantTrackerViewModel::updateCurrentPlant,
                    onCancel = onCancel,
                    onSubmit = onSubmit
                )
            }
        }
    ) {
        content()
    }
}*/

@Composable
fun SheetHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            modifier = modifier.padding(8.dp),
            text = stringResource(R.string.bottom_sheet_headline),
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )

/*
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                "Notifications in Jetpack Compose",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 100.dp)
            )

            // simple notification button
            Button(onClick = {
                showSimpleNotification(
                    context,
                    channelId,
                    notificationId,
                    "Simple notification",
                    "This is a simple notification with default priority."
                )
            }, modifier = Modifier.padding(top = 16.dp)) {
                Text(text = "Simple Notification")
            }
*/
            Divider()
        }
    }
/*OVAJ JE BIO NE ZAKOMENTIRAN*/
/*
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerRow(
    selectedDate: LocalDate?,
    onDateChange: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val datePickerDialog = remember {
        DatePickerDialog(
            LocalContext.current,
            { _, year, month, dayOfMonth -> onDateChange(LocalDate.of(year, month + 1, dayOfMonth)) },
            selectedDate?.year ?: LocalDate.now().year,
            selectedDate?.monthValue?.minus(1) ?: LocalDate.now().monthValue - 1,
            selectedDate?.dayOfMonth ?: LocalDate.now().dayOfMonth
        )
    }
    InputRow(inputLabel = "Notification Date", modifier = modifier) {
        OutlinedButton(onClick = { datePickerDialog.show() }) {
            Text(text = selectedDate?.toString() ?: "Select Date")
        }
    }
}
*/

/* I OVAJ
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimePickerRow(
    selectedTime: LocalTime?,
    onTimeChange: (LocalTime) -> Unit,
    modifier: Modifier = Modifier
) {
    val timePickerDialog = remember {
        TimePickerDialog(
            LocalContext.current,
            { _, hourOfDay, minute -> onTimeChange(LocalTime.of(hourOfDay, minute)) },
            selectedTime?.hour ?: LocalTime.now().hour,
            selectedTime?.minute ?: LocalTime.now().minute,
            true
        )
    }
    InputRow(inputLabel = "Notification Time", modifier = modifier) {
        OutlinedButton(onClick = { timePickerDialog.show() }) {
            Text(text = selectedTime?.toString() ?: "Select Time")
        }
    }
}*/
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SheetForm(
    plant: Plant,
    onUpdatePlant: (Plant) -> Unit,
    onCancel: () -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(horizontal = 16.dp)) {
        TextInputRow(
            inputLabel = stringResource(R.string.plant_name),
            fieldValue = plant.name,
            onValueChange = { name -> onUpdatePlant(plant.copy(name = name)) }
        )
        TextInputRow(
            inputLabel = stringResource(R.string.plant_description),
            fieldValue = plant.description,
            onValueChange = { description -> onUpdatePlant(plant.copy(description = description)) }
        )
        ColorSpinnerRow(
            colorSpinnerPosition = findColorIndex(plant.color),
            onColorChange = { color ->
                onUpdatePlant(plant.copy(color = PlantColor.values()[color].name))
            }
        )
        ButtonRow(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onCancel = onCancel,
            onSubmit = onSubmit,
            submitButtonEnabled = plant.name.isNotEmpty()
        )
       /* DatePickerRow(
            selectedDate = plant.notificationDate,
            onDateChange = { date -> onUpdatePlant(plant.copy(notificationDate = date)) }
        )
        TimePickerRow(
            selectedTime = plant.notificationTime,
            onTimeChange = { time -> onUpdatePlant(plant.copy(notificationTime = time)) }
        )
        ButtonRow(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onCancel = onCancel,
            onSubmit = onSubmit,
            submitButtonEnabled = plant.name.isNotEmpty()
        )*/
    }
}

/*
@Composable
fun SheetForm(
    plant: Plant,
    onUpdatePlant: (Plant) -> Unit,
    onCancel: () -> Unit,
    onSubmit: @Composable (String, Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var selectedTime by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    val context = LocalContext.current

    Column(modifier.padding(horizontal = 16.dp)) {
        TextInputRow(
            inputLabel = stringResource(R.string.plant_name),
            fieldValue = plant.name,
            onValueChange = { name -> onUpdatePlant(plant.copy(name = name)) }
        )
        TextInputRow(
            inputLabel = stringResource(R.string.plant_description),
            fieldValue = plant.description,
            onValueChange = { description -> onUpdatePlant(plant.copy(description = description)) }
        )
        ColorSpinnerRow(
            colorSpinnerPosition = findColorIndex(plant.color),
            onColorChange = { color ->
                onUpdatePlant(plant.copy(color = PlantColor.values()[color].name))
            }
        )

        // Date Picker Button
        Button(onClick = {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, dayOfMonth)
                    selectedDate = calendar.timeInMillis
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            ).show()
        }) {
            Text(text = selectedDate?.let {
                val calendar = Calendar.getInstance().apply { timeInMillis = it }
                "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
            } ?: "Select Date")
        }

        // Time Picker Button
        Button(onClick = {
            val calendar = Calendar.getInstance()
            TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    selectedTime = Pair(hourOfDay, minute)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }) {
            Text(text = selectedTime?.let {
                "${it.first}:${it.second}"
            } ?: "Select Time")
        }

        ButtonRow(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onCancel = onCancel,
            onSubmit = {
                val calendar = Calendar.getInstance()
                selectedDate?.let { calendar.timeInMillis = it }
                selectedTime?.let {
                    calendar.set(Calendar.HOUR_OF_DAY, it.first)
                    calendar.set(Calendar.MINUTE, it.second)
                }
                val notificationTime = calendar.timeInMillis
                onSubmit(plant.name, notificationTime)
            },
            submitButtonEnabled = plant.name.isNotEmpty() && selectedDate != null && selectedTime != null
        )
    }
}*/

/*
@Composable
fun SheetForm(
    plant: Plant,
    onUpdatePlant: (Plant) -> Unit,
    onCancel: () -> Unit,
    onSubmit: @Composable (String, Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var selectedTime by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    val context = LocalContext.current

    Column(modifier.padding(horizontal = 16.dp)) {
        TextInputRow(
            inputLabel = stringResource(R.string.plant_name),
            fieldValue = plant.name,
            onValueChange = { name -> onUpdatePlant(plant.copy(name = name)) }
        )
        TextInputRow(
            inputLabel = stringResource(R.string.plant_description),
            fieldValue = plant.description,
            onValueChange = { description -> onUpdatePlant(plant.copy(description = description)) }
        )
        ColorSpinnerRow(
            colorSpinnerPosition = findColorIndex(plant.color),
            onColorChange = { color ->
                onUpdatePlant(plant.copy(color = PlantColor.values()[color].name))
            }
        )

        // Date Picker Button
        Button(onClick = {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, dayOfMonth)
                    selectedDate = calendar.timeInMillis
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            ).show()
        }) {
            Text(text = selectedDate?.let {
                val calendar = Calendar.getInstance().apply { timeInMillis = it }
                "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
            } ?: "Select Date")
        }

        // Time Picker Button
        Button(onClick = {
            val calendar = Calendar.getInstance()
            TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    selectedTime = Pair(hourOfDay, minute)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }) {
            Text(text = selectedTime?.let {
                "${it.first}:${it.second}"
            } ?: "Select Time")
        }

        ButtonRow(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onCancel = onCancel,
            onSubmit = {
                val calendar = Calendar.getInstance()
                selectedDate?.let { calendar.timeInMillis = it }
                selectedTime?.let {
                    calendar.set(Calendar.HOUR_OF_DAY, it.first)
                    calendar.set(Calendar.MINUTE, it.second)
                }
                val notificationTime = calendar.timeInMillis
                onSubmit(plant.name, notificationTime)
            },
            submitButtonEnabled = plant.name.isNotEmpty() && selectedDate != null && selectedTime != null
        )
    }
}*/

/*
@Composable
fun SheetForm(
    plant: Plant,
    onUpdatePlant: (Plant) -> Unit,
    onCancel: () -> Unit,
    onSubmit: @Composable (String, Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var selectedTime by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    val datePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickActivityResult(),
        onResult = { result ->
            if (result != null) {
                selectedDate = result
            }
        }
    )

    val timePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickActivityResult(),
        onResult = { result ->
            if (result != null) {
                selectedTime = Pair(result.getIntExtra("hour", 0), result.getIntExtra("minute", 0))
            }
        }
    )

    Column(modifier.padding(horizontal = 16.dp)) {
        TextInputRow(
            inputLabel = stringResource(R.string.plant_name),
            fieldValue = plant.name,
            onValueChange = { name -> onUpdatePlant(plant.copy(name = name)) }
        )
        TextInputRow(
            inputLabel = stringResource(R.string.plant_description),
            fieldValue = plant.description,
            onValueChange = { description -> onUpdatePlant(plant.copy(description = description)) }
        )
        ColorSpinnerRow(
            colorSpinnerPosition = findColorIndex(plant.color),
            onColorChange = { color ->
                onUpdatePlant(plant.copy(color = PlantColor.values()[color].name))
            }
        )

        // Date Picker Button
        Button(onClick = {
            datePickerLauncher.launch(PickerIntent(DatePickerActivity::class.java))
        }) {
            Text(text = selectedDate?.let {
                val calendar = Calendar.getInstance().apply { timeInMillis = it }
                "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
            } ?: "Select Date")
        }

        // Time Picker Button
        Button(onClick = {
            timePickerLauncher.launch(PickerIntent(TimePickerActivity::class.java))
        }) {
            Text(text = selectedTime?.let {
                "${it.first}:${it.second}"
            } ?: "Select Time")
        }

        ButtonRow(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onCancel = onCancel,
            onSubmit = {
                val calendar = Calendar.getInstance()
                selectedDate?.let { calendar.timeInMillis = it }
                selectedTime?.let {
                    calendar.set(Calendar.HOUR_OF_DAY, it.first)
                    calendar.set(Calendar.MINUTE, it.second)
                }
                val notificationTime = calendar.timeInMillis
                onSubmit(plant.name, notificationTime)
            },
            submitButtonEnabled = plant.name.isNotEmpty() && selectedDate != null && selectedTime != null
        )
    }
}
*/

/*
fun PickerIntent(pickerClass: Class<out AppCompatActivity>): Intent {
    return Intent(ContextProvider.appContext, pickerClass)
}
*/
/*
@Composable
fun SheetForm(
    plant: Plant,
    onUpdatePlant: (Plant) -> Unit,
    onCancel: () -> Unit,
    onSubmit: (String, Long, Context) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var selectedTime by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    val context = LocalContext.current

    Column(modifier.padding(horizontal = 16.dp)) {
        TextInputRow(
            inputLabel = stringResource(R.string.plant_name),
            fieldValue = plant.name,
            onValueChange = { name -> onUpdatePlant(plant.copy(name = name)) }
        )
        TextInputRow(
            inputLabel = stringResource(R.string.plant_description),
            fieldValue = plant.description,
            onValueChange = { description -> onUpdatePlant(plant.copy(description = description)) }
        )
        ColorSpinnerRow(
            colorSpinnerPosition = findColorIndex(plant.color),
            onColorChange = { color ->
                onUpdatePlant(plant.copy(color = PlantColor.values()[color].name))
            }
        )

        // Date Picker Button
        Button(onClick = {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                selectedDate = it
            }
            datePicker.show((context as AppCompatActivity).supportFragmentManager, "datePicker")
        }) {
            Text(text = selectedDate?.let {
                val calendar = Calendar.getInstance().apply { timeInMillis = it }
                "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
            } ?: "Select Date")
        }

        // Time Picker Button
        Button(onClick = {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePicker.addOnPositiveButtonClickListener {
                selectedTime = Pair(timePicker.hour, timePicker.minute)
            }
            timePicker.show((context as AppCompatActivity).supportFragmentManager, "timePicker")
        }) {
            Text(text = selectedTime?.let {
                "${it.first}:${it.second}"
            } ?: "Select Time")
        }

        ButtonRow(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onCancel = onCancel,
            onSubmit = {
                val calendar = Calendar.getInstance()
                selectedDate?.let { calendar.timeInMillis = it }
                selectedTime?.let {
                    calendar.set(Calendar.HOUR_OF_DAY, it.first)
                    calendar.set(Calendar.MINUTE, it.second)
                }
                val notificationTime = calendar.timeInMillis
                onSubmit(plant.name, notificationTime, context)
            },
            submitButtonEnabled = plant.name.isNotEmpty() && selectedDate != null && selectedTime != null
        )
    }
}*/
/*
@Composable
fun SheetForm(
    plant: Plant,
    onUpdatePlant: (Plant) -> Unit,
    onCancel: () -> Unit,
    onSubmit: (Any?, Any?) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var selectedTime by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    val datePicker = MaterialDatePicker.Builder.datePicker().build()
    val timePicker = MaterialTimePicker.Builder()
        .setTimeFormat(TimeFormat.CLOCK_24H)
        .build()

    Column(modifier.padding(horizontal = 16.dp)) {
        TextInputRow(
            inputLabel = stringResource(R.string.plant_name),
            fieldValue = plant.name,
            onValueChange = { name -> onUpdatePlant(plant.copy(name = name)) }
        )
        TextInputRow(
            inputLabel = stringResource(R.string.plant_description),
            fieldValue = plant.description,
            onValueChange = { description -> onUpdatePlant(plant.copy(description = description)) }
        )
        ColorSpinnerRow(
            colorSpinnerPosition = findColorIndex(plant.color),
            onColorChange = { color ->
                onUpdatePlant(plant.copy(color = PlantColor.values()[color].name))
            }
        )

        // Date Picker Button
        Button(onClick = {
            datePicker.show((LocalContext.current as AppCompatActivity).supportFragmentManager, "datePicker")
        }) {
            Text(text = selectedDate?.let {
                val calendar = Calendar.getInstance().apply { timeInMillis = it }
                "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
            } ?: "Select Date")
        }

        // Time Picker Button
        Button(onClick = {
            timePicker.show((LocalContext.current as AppCompatActivity).supportFragmentManager, "timePicker")
        }) {
            Text(text = selectedTime?.let {
                "${it.first}:${it.second}"
            } ?: "Select Time")
        }

        // Listeners for Date and Time Picker
        datePicker.addOnPositiveButtonClickListener {
            selectedDate = it
        }

        timePicker.addOnPositiveButtonClickListener {
            selectedTime = Pair(timePicker.hour, timePicker.minute)
        }

        ButtonRow(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onCancel = onCancel,
            onSubmit = {
                val calendar = Calendar.getInstance()
                selectedDate?.let { calendar.timeInMillis = it }
                selectedTime?.let {
                    calendar.set(Calendar.HOUR_OF_DAY, it.first)
                    calendar.set(Calendar.MINUTE, it.second)
                }
                val notificationTime = calendar.timeInMillis
                onSubmit(plant.name, notificationTime)
            },
            submitButtonEnabled = plant.name.isNotEmpty() && selectedDate != null && selectedTime != null
        )
        /*ButtonRow(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onCancel = onCancel,
            onSubmit = onSubmit,
            submitButtonEnabled = plant.name.isNotEmpty()
        )*/
    }
}*/

/* I OVAJ !!!
@Composable
fun ButtonRow(
    onCancel: () -> Unit,
    onSubmit: @Composable (Any?, Any?) -> Unit,
    submitButtonEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        Button(onClick = onCancel, modifier = Modifier.weight(1f)) {
            Text("Cancel")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = onSubmit, enabled = submitButtonEnabled, modifier = Modifier.weight(1f)) {
            Text("Submit")
        }
    }
}*/


@Composable
fun ButtonRow(
    onCancel: () -> Unit,
    onSubmit: () -> Unit,
    submitButtonEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        OutlinedButton(
            onClick = onCancel,
            border = null
        ) {
            Text(stringResource(android.R.string.cancel).uppercase(Locale.getDefault()))
        }
        Button(
            onClick = onSubmit,
            enabled = submitButtonEnabled
        ) {
            Text(stringResource(R.string.save).uppercase(Locale.getDefault()))
        }
    }
}

@Composable
fun TextInputRow(
    inputLabel: String,
    fieldValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    InputRow(inputLabel, modifier) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = fieldValue,
            onValueChange = onValueChange,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.surface,
                unfocusedContainerColor = colorScheme.surface,
                disabledContainerColor = colorScheme.surface,
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
    }
}

@Composable
fun InputRow(
    inputLabel: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier.padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = inputLabel,
            fontWeight = FontWeight.SemiBold,
            modifier = modifier
                .weight(1f)
                .padding(end = 8.dp),
        )
        Box(modifier = Modifier.weight(2f)) {
            content()
        }
    }
}
    // shows notification with a title and one-line content text
  /*  fun showSimpleNotification(
        context: Context,
        channelId: String,
        notificationId: Int,
        textTitle: String,
        textContent: String,
        priority: Int = NotificationCompat.PRIORITY_DEFAULT
    ) {
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_edit_location)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setPriority(priority)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notify(notificationId, builder.build())
        }
    }

*/
    fun createNotificationChannel(channelId: String, context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "MyTestChannel"
            val descriptionText = "My important test channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
/*
    @Composable
    fun NotificationApp() {
        val context = LocalContext.current
        val channelId = "MyTestChannel"
        val notificationId = 0
        val myBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.notify_plant)
        val bigText = "This is my test notification in one line. Made it longer " +
                "by setting the setStyle property. " +
                "It should not fit in one line anymore, " +
                "rather show as a longer notification content."

        LaunchedEffect(Unit) {
            createNotificationChannel(channelId, context)
        }
    }

*/




