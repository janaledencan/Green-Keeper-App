package com.jana.greenkeeper.view.bottomsheet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationBottomSheet(
    sheetScaffoldState: BottomSheetScaffoldState,
    onCancel: () -> Unit,
    onSubmit:  (String, String, Long) -> Unit,  // Pass title, message, and time
    modifier: Modifier = Modifier
) {
    var pickerDate by remember { mutableStateOf(LocalDate.now()) }
    var pickedTime by remember { mutableStateOf(LocalTime.NOON) }
    var title by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val formattedDate by remember { derivedStateOf { DateTimeFormatter.ofPattern("MMM dd yyyy").format(pickerDate) } }
    val formattedTime by remember { derivedStateOf { DateTimeFormatter.ofPattern("hh:mm").format(pickedTime) } }

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = sheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = message,
                    onValueChange = { message = it },
                    label = { Text("Message") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { dateDialogState.show() }) {
                    Text(text = "Pick date")
                }
                Text(text = formattedDate)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { timeDialogState.show() }) {
                    Text(text = "Pick time")
                }
                Text(text = formattedTime)
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Button(onClick = {
                        val calendar = Calendar.getInstance().apply {
                            set(pickerDate.year, pickerDate.monthValue - 1, pickerDate.dayOfMonth, pickedTime.hour, pickedTime.minute)
                        }
                        onSubmit(title, message, calendar.timeInMillis)
                    }) {
                        Text(text = "Confirm")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = onCancel) {
                        Text(text = "Cancel")
                    }
                }
            }
        }
    ){
        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = "Ok") { /* handle Ok */ }
                negativeButton(text = "Cancel")
            }
        ) {
            datepicker(initialDate = LocalDate.now(), title = "Pick a date") {
                pickerDate = it
            }
        }

        MaterialDialog(
            dialogState = timeDialogState,
            buttons = {
                positiveButton(text = "Ok") { /* handle Ok */ }
                negativeButton(text = "Cancel")
            }
        ) {
            timepicker(initialTime = LocalTime.NOON, title = "Pick a time") {
                pickedTime = it
            }
        }

    }
}

