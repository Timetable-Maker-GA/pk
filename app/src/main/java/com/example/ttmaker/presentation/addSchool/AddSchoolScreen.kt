@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ttmaker.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.ttmaker.presentation.addSchool.components.Form
import com.example.ttmaker.presentation.addSchool.components.TextFileInput
import com.ntech.ttmaker.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSchoolScreen(
    navController: NavHostController
) {
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
//            .height(200.dp)
            .verticalScroll(scrollState)
    )

    {
        TabRow(selectedTabIndex = selectedTabIndex,

            // Customize TabRow indicator color
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = colorResource(id = R.color.buttonLightPale) // Set the color of the indicator to blue
                )
            }) {
            Tab(
                text = { Text("File Input",
                    color= colorResource(id = R.color.textLightBlueHeavy)
                ) },
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 }
            )
            Tab(
                text = { Text("Form Input",
                    color= colorResource(id = R.color.textLightBlueHeavy)
                ) },
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 }
            )

        }

        when (selectedTabIndex) {
            0 -> TextFileInput()
            1 -> Form()
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddSchoolScreen(
//    navController: NavHostController
//) {
//    var state by remember { mutableIntStateOf(0) }
//    val titles = listOf("Basic Info", "Add Schedule ")
//
//    val openDialog: MutableState<Boolean> = remember {
//        mutableStateOf<Boolean>(false)
//    }
//    val currentTime = Calendar.getInstance()
//
//    val schoolStartTime = rememberTimePickerState()
//    val schoolEndTime = rememberTimePickerState()
//    val lunchStartTime = rememberTimePickerState()
//    val lunchEndTime = rememberTimePickerState()
//    Scaffold(containerColor = Color(0xFFF2F3F4), bottomBar = {
//        TabControls(state, setState = { v ->
//            Log.d("TabControls", "s${v}")
//            state = (v)
//        })
//    }) { innerPadding ->
//        Column {
//            PrimaryTabRow(
//                selectedTabIndex = state,
//                contentColor = Color.Blue,
//                containerColor = Color.Transparent,
//                modifier = Modifier.padding(innerPadding)
//            ) {
//                titles.forEachIndexed { index, title ->
//                    Tab(selected = state == index, onClick = { state = index }, text = {
//                        Text(
//                            text = title,
//                            maxLines = 2,
//                            fontFamily = ManropeFontFamily,
//                            fontWeight = FontWeight.Medium,
//                            overflow = TextOverflow.Ellipsis,
//                            color = Color.Black
//                        )
//                    })
//                }
//            }
//            Surface(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxHeight(),
//                contentColor = Color.Transparent,
//                color = Color.Transparent,
//                ) {
//                when (state) {
//                    0 -> BasicInfoForm()
//                    1 -> {
//                        Column {
//                            CustomTimePicker("School start time", schoolStartTime)
//                            CustomTimePicker("School end time", schoolEndTime)
//                            CustomTimePicker("Lunch start time", lunchStartTime)
//                            CustomTimePicker("Lunch end time", lunchEndTime)
//
//                        }
//                    }
//                }
//            }
//        }
//    }
//}


//@Composable
//fun CustomTimePicker(
//    label: String, timePickerState: TimePickerState
//) {
//    val openDialog: MutableState<Boolean> = remember {
//        mutableStateOf<Boolean>(false)
//    }
//    Row(
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth()
//
//            .clip(RoundedCornerShape(16.dp))
//            .background(Color.White)
//            .padding(8.dp)
//            .padding(horizontal = 8.dp),
//
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//
//    ) {
//        Text(
//            text = label,
//            style = MaterialTheme.typography.bodyMedium,
//            fontFamily = manrope,
//            color = Color.Black
//        )
//        Button(
//            onClick = {
//                openDialog.value = !openDialog.value
//            }, colors = ButtonDefaults.buttonColors(
//                contentColor = Color.Black, backgroundColor = Color(0xFFF1F1F5),
//            ), shape = RoundedCornerShape(16.dp), elevation = ButtonDefaults.elevation(0.dp)
//
//        ) {
//            Text(
//                text = "${timePickerState.hour} : ${timePickerState.minute}",
//                color = Color.Black,
//                style = MaterialTheme.typography.bodyMedium,
//                fontFamily = manrope,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Medium
//            )
//        }
//        if (openDialog.value) {
//            TimePickerDialog(onDismiss = { openDialog.value = false },
//                onConfirm = { openDialog.value = false }) {
//                TimePicker(
//                    state = timePickerState,
//                )
//            }
//        }
//    }
//}
//
//
//@Composable
//fun TimePickerDialog(
//    onDismiss: () -> Unit, onConfirm: () -> Unit, content: @Composable () -> Unit
//) {
//    AlertDialog(onDismissRequest = onDismiss, dismissButton = {
//        TextButton(onClick = { onDismiss() }) {
//            Text("Dismiss")
//        }
//    }, confirmButton = {
//        TextButton(onClick = { onConfirm() }) {
//            Text("OK")
//        }
//    }, text = { content() })
//}


