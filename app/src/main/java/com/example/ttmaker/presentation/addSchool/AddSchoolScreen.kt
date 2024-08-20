@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ttmaker.presentation


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ttmaker.ManropeFontFamily
import com.example.ttmaker.presentation.addSchool.components.BasicInfoForm
import com.example.ttmaker.presentation.home.shared.TabControls
import com.example.ui.theme.manrope
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSchoolScreen(
    navController: NavHostController
) {
    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("Basic Info", "Add Schedule ")

    val openDialog: MutableState<Boolean> = remember {
        mutableStateOf<Boolean>(false)
    }
    val currentTime = Calendar.getInstance()

    val schoolStartTime = rememberTimePickerState()
    val schoolEndTime = rememberTimePickerState()
    val lunchStartTime = rememberTimePickerState()
    val lunchEndTime = rememberTimePickerState()
    Scaffold(containerColor = Color(0xFFF2F3F4), bottomBar = {
        TabControls(state, setState = { v ->
            Log.d("TabControls", "s${v}")
            state = (v)
        })
    }) { innerPadding ->
        Column {
            PrimaryTabRow(
                selectedTabIndex = state,
                contentColor = Color.Blue,
                containerColor = Color.Transparent,
                modifier = Modifier.padding(innerPadding)
            ) {
                titles.forEachIndexed { index, title ->
                    Tab(selected = state == index, onClick = { state = index }, text = {
                        Text(
                            text = title,
                            maxLines = 2,
                            fontFamily = ManropeFontFamily,
                            fontWeight = FontWeight.Medium,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black
                        )
                    })
                }
            }

            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentColor = Color.Transparent,
                color = Color.Transparent,
                ) {
                when (state) {
                    0 -> BasicInfoForm()
                    1 -> {
                        Column {
                            CustomTimePicker("School start time", schoolStartTime)
                            CustomTimePicker("School end time", schoolEndTime)
                            CustomTimePicker("Lunch start time", lunchStartTime)
                            CustomTimePicker("Lunch end time", lunchEndTime)

                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CustomTimePicker(
    label: String, timePickerState: TimePickerState
) {
    val openDialog: MutableState<Boolean> = remember {
        mutableStateOf<Boolean>(false)
    }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()

            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(8.dp)
            .padding(horizontal = 8.dp),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = manrope,
            color = Color.Black
        )
        Button(
            onClick = {
                openDialog.value = !openDialog.value
            }, colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black, backgroundColor = Color(0xFFF1F1F5),
            ), shape = RoundedCornerShape(16.dp), elevation = ButtonDefaults.elevation(0.dp)

        ) {
            Text(
                text = "${timePickerState.hour} : ${timePickerState.minute}",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = manrope,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
        if (openDialog.value) {
            TimePickerDialog(onDismiss = { openDialog.value = false },
                onConfirm = { openDialog.value = false }) {
                TimePicker(
                    state = timePickerState,
                )
            }
        }
    }
}


@Composable
fun TimePickerDialog(
    onDismiss: () -> Unit, onConfirm: () -> Unit, content: @Composable () -> Unit
) {
    AlertDialog(onDismissRequest = onDismiss, dismissButton = {
        TextButton(onClick = { onDismiss() }) {
            Text("Dismiss")
        }
    }, confirmButton = {
        TextButton(onClick = { onConfirm() }) {
            Text("OK")
        }
    }, text = { content() })
}


