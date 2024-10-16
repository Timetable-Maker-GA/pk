package com.example.ttmaker.presentation.School

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ttmaker.TTMakerApplication
import com.example.ttmaker.activity.CreateSchoolActivity
import com.example.ttmaker.activity.CreateTTActivity
import com.example.ttmaker.activity.MainActivity
import com.example.ttmaker.presentation.addTimeTable.components.DisplayTimetables
import com.ntech.ttmaker.R



@Composable
fun SchoolScreen(id: Int) {
    val context = LocalContext.current
    val app = context.applicationContext as TTMakerApplication
    val vm: SchoolViewModel = viewModel(factory = SchoolViewModelFactory(app.schoolRepository))

    LaunchedEffect(id) {
        vm.fetchSchoolDetails(id)
    }
    val schoolDetails = vm.schoolDetails.collectAsState().value

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                schoolDetails?.let { school ->
                    // School Header
                    Text(
                        text = school.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = colorResource(id = R.color.headingLightBlueHeavy),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // School Details Section
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.bgDark)) // Using color resource
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) { // Reduced padding
                            // Teacher Information
                            Text(
                                text = "Teachers (${school.teachers.size}):",
                                style = MaterialTheme.typography.bodyMedium // Reduced style for compactness
                            )
                            Text(
                                text = "- ${school.teachers.joinToString(", ") { teacher -> teacher.name }}",
                                style = MaterialTheme.typography.bodySmall, // Further reduced style for compactness
                                modifier = Modifier.padding(2.dp) // Reduced padding
                            )

                            // Subjects Information
                            Text(
                                text = "Subjects (${school.subjects.size}):",
                                style = MaterialTheme.typography.bodyMedium // Reduced style for compactness
                            )
                            Text(
                                text = "- ${school.subjects.joinToString(", ")}}",
                                style = MaterialTheme.typography.bodySmall, // Further reduced style for compactness
                                modifier = Modifier.padding(2.dp) // Reduced padding
                            )

                            // Removed unnecessary Spacer to reduce height

                            // Timetable Information
                            Text(
                                text = "Total Timetables: ${school.timetableCount}",
                                style = MaterialTheme.typography.bodySmall // Reduced style for compactness
                            )
                            Text(
                                text = "Days: ${school.DAYS}, Hours: ${school.HOURS}",
                                style = MaterialTheme.typography.bodySmall // Reduced style for compactness
                            )
                            Text(
                                text = "Population Size: ${school.POPULATION_SIZE}, Generations: ${school.GENERATIONS}",
                                style = MaterialTheme.typography.bodySmall // Reduced style for compactness
                            )

                            // Removed unnecessary Spacer to reduce height

                            // Created At (Formatted Date)
                            val createdAtFormatted = remember {
                                java.text.SimpleDateFormat(
                                    "dd MMM yyyy",
                                    java.util.Locale.getDefault()
                                )
                                    .format(java.util.Date(school.createdAt))
                            }
                            Text(
                                text = "Created At: $createdAtFormatted",
                                style = MaterialTheme.typography.bodySmall // Reduced style for compactness
                            )
                        }
                    }

                    val schoolDetailsState = vm.schoolDetails.collectAsState(initial = null)
                    // Use the collected state
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState()) // Enable vertical scrolling
                            .height((LocalConfiguration.current.screenHeightDp * 0.5).dp) // Set height to 40% of screen height
                    ) {
                        schoolDetailsState.value?.let { schoolDetails ->
                            DisplayTimetables(allTimetables = schoolDetails.allTimetables)
                        }
                    }
                    // Button to Create Timetable
                    Button(
                        onClick = {
                            val intent = Intent(context, CreateTTActivity::class.java).apply {
                                putExtra("SCHOOL_ID", id)
                            }
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.buttonLightHeavy)),

                        ) {
                        Text(
                            text = "Create Timetable",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White // Set text color to white for contrast
                        )
                    }
                }
            }
        }
    }
}
