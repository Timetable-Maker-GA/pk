package com.example.ttmaker.presentation.addTimeTable

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.ttmaker.R
import com.example.ttmaker.TTMakerApplication
import com.example.ttmaker.adsContainer.InterstitialAdContainerEXCEL
import com.example.ttmaker.adsContainer.InterstitialAdContainerPDF
import com.example.ttmaker.presentation.addTimeTable.components.LoadingScreen

//import com.example.ttmaker.presentation.addTimeTable.components.DisplayTimetables
//import com.example.ttmaker.presentation.addTimeTable.components.SubjectSelection
//import com.example.ttmaker.presentation.addTimeTable.components.LoadingScreen
//import com.example.ttmaker.util.saveAsExcel
//import com.example.ttmaker.util.saveAsPDF
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.input.KeyboardType
import com.example.ttmaker.activity.CreateTTActivity
import com.example.ttmaker.presentation.addTimeTable.components.DisplayTimetables
import com.example.ttmaker.presentation.addTimeTable.components.SubjectSelection
import com.ntech.ttmaker.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTimeTableScreen(
    schoolId: Int
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val app = context.applicationContext as TTMakerApplication
    val vm: AddTimeTableViewModel = viewModel(factory = AddTimeTableViewModelFactory(app.schoolRepository))

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    LaunchedEffect(schoolId) {
        Log.d("SCHOOL ID: ", schoolId.toString())
        vm.updateSelectedSchool(schoolId)
    }

    val createFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/pdf")
    ) { uri: Uri? ->
        uri?.let {
            InterstitialAdContainerPDF(context as CreateTTActivity)
                vm.saveAsPDF(context, it)
        }
    }

    val createFileLauncherExcel = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    ) { uri: Uri? ->
        uri?.let {
            InterstitialAdContainerEXCEL(context as CreateTTActivity)
                vm.saveAsExcel(context, it)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(screenWidth * 0.02f)
            .verticalScroll(scrollState)
            .background(Color.White)
    ) {
        if (vm.isCreating.value) {
            LoadingScreen(vm.gen.value, vm.level.value.toInt(),
                population = vm.selectedSchool.value!!.POPULATION_SIZE,
                generation = vm.selectedSchool.value!!.GENERATIONS)
        } else {
            Column {
                Text(
                    text = "Select Institute",
                    modifier = Modifier.padding(top = 4.dp)
                )
                Box {
                    Text(
                        text = vm.selectedSchool.value?.name ?: "Choose an institute",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { vm.updateExpanded(true) }
                            .background(Color.LightGray)
                            .padding(16.dp)
                    )
                    DropdownMenu(
                        expanded = vm.expanded.value,
                        onDismissRequest = { vm.updateExpanded(false) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        vm.schoolList.value.forEach { school ->
                            DropdownMenuItem(
                                text = { Text(school.name) },
                                onClick = {
                                    vm.updateSelectedSchool(school.id)
                                    vm.clearSubPeriodsPerWeek()
                                    vm.updateExpanded(false)
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = vm.className.value, // Access the value of className
                        onValueChange = { vm.updateClassName(it) }, // Update the className when text changes
                        label = { Text("Class No") }, // Set the label for the field
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 4.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )
                    OutlinedTextField(
                        value = vm.section.value, // Access the value of section
                        onValueChange = { vm.updateSection(it) }, // Update the section when text changes
                        label = { Text("Section") }, // Set the label for the field
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 4.dp)
                    )
                }
                val totalPeriodsGiven = vm.subPeriodsPerWeek.value.values.sum()

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text(
                            text = "Timetable Optimization: ${vm.level.value.toInt()}",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.labelLarge
                        )
                        Text(
                            text = "Higher values take longer",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = "but improve schedule quality",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    Slider(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        value = vm.level.value,
                        onValueChange = { vm.updateLevel(it) },
                        valueRange = 1f..5f,
                        steps = 5
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            vm.selectedSchool.value?.let {
                                createFileLauncher.launch(it.name + "_Report.pdf")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.buttonLightHeavy))
                    ) {
                        Text("Save as PDF")
                    }

                    Button(
                        onClick = {
                            vm.selectedSchool.value?.let {
                                createFileLauncherExcel.launch(it.name + "_Report.xlsx")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.buttonLightHeavy))
                    ) {
                        Text("Save as Excel")
                    }
                }

                Text(
                    text = "Allocated: $totalPeriodsGiven / ${ (vm.selectedSchool.value?.HOURS ?: 0) * (vm.selectedSchool.value?.DAYS ?: 0)} periods",
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = Color.Gray
                )

                if (vm.selectedSchool.value == null) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight*0.2f)
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Start by Selecting School",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                vm.selectedSchool.value?.let {
                    SubjectSelection(it, vm.className.value, vm.subPeriodsPerWeek.value, updateSubPeriods = { key, value -> vm.updateSubPeriodsPerWeek(key, value) })
                    HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
                    DisplayTimetables(it.allTimetables)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {

//                            vm.updateIsCreating(!vm.isCreating.value)
                           vm.createTT(context)

                        },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.buttonLightHeavy)),
                        modifier = Modifier.padding(vertical = 16.dp)
                    ) {
                        Text("Create Timetable")
                    }
                }
//            }
        }
    }
}}