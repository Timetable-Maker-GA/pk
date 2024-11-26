package com.example.ttmaker.presentation.School

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ttmaker.TTMakerApplication
import com.example.ttmaker.activity.CreateTTActivity
import com.example.ttmaker.model.SchoolEntity
import com.example.ttmaker.presentation.shared.DisplayTimetables
import com.example.ttmaker.presentation.shared.TimetableCard
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
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                schoolDetails?.let { school ->
                    // School Header
                    Text(
                        text = school.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = colorResource(id = R.color.headingLightBlueHeavy),
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    val schoolDetailsState = vm.schoolDetails.collectAsState(initial = null)


                    // Scrollable Top + Middle Section
                    LazyColumn(
                        modifier = Modifier.weight(1f) // Take remaining space
                    ) {
                        item {
                            // Top Section: BASIC INFO
                            BasicInfo(school = school)
                        }
                        item {
                            Spacer(modifier = Modifier.height(8.dp)) // Add spacing
                        }
                        // Middle Section: Timetables
                        schoolDetailsState.value?.let { schoolDetails ->
                            val sortedTimetables =
                                schoolDetails.allTimetables.sortedByDescending { it.createdAt }
                            items(sortedTimetables) { timetable ->
                                TimetableCard(timetable)
                            }

                        }
                    }

                    // Bottom Section: Button
                    Button(
                        onClick = {
                            val intent = Intent(context, CreateTTActivity::class.java).apply {
                                putExtra("SCHOOL_ID", id)
                            }
                            context.startActivity(intent)
                        },
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


@Composable
fun BasicInfo(school: SchoolEntity) {
    // School Details Section
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), // Outer padding around the card
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.bgDark)),
//        elevation = 8.dp // Elevation to make the card pop
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp) // Inner padding for better spacing
                .fillMaxWidth()
        ) {
            // Teachers Information
            Text(
                text = "Teachers (${school.teachers.size}):",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold), // Bold title
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = school.teachers.joinToString(", ") { teacher -> teacher.name },
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Subjects Information
            Text(
                text = "Subjects (${school.subjects.size}):",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = school.subjects.joinToString(", "),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Timetable Information
            Text(
                text = "Timetable Info:",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Total Timetables: ${school.timetableCount}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Days: ${school.DAYS}, Hours: ${school.HOURS}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Population Size: ${school.POPULATION_SIZE}, Generations: ${school.GENERATIONS}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Created At Information
            val createdAtFormatted = remember {
                java.text.SimpleDateFormat(
                    "dd MMM yyyy",
                    java.util.Locale.getDefault()
                ).format(java.util.Date(school.createdAt))
            }
            Text(
                text = "Created At: $createdAtFormatted",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}



//@Composable
//fun SchoolScreen(id: Int) {
//    val context = LocalContext.current
//    val app = context.applicationContext as TTMakerApplication
//    val vm: SchoolViewModel = viewModel(factory = SchoolViewModelFactory(app.schoolRepository))
//
//    LaunchedEffect(id) {
//        vm.fetchSchoolDetails(id)
//    }
//    val schoolDetails = vm.schoolDetails.collectAsState().value
//
//    MaterialTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                schoolDetails?.let { school ->
//                    // School Header
//                    Text(
//                        text = school.name,
//                        style = MaterialTheme.typography.headlineMedium,
//                        color = colorResource(id = R.color.headingLightBlueHeavy),
//                        modifier = Modifier.padding(bottom = 16.dp)
//                    )
//
//
//                    val schoolDetailsState = vm.schoolDetails.collectAsState(initial = null)
//                    // Use the collected state
//                    Column(
//
//                        modifier = Modifier
//                            .verticalScroll(rememberScrollState()) // Enable vertical scrolling
//                            .height((LocalConfiguration.current.screenHeightDp * 0.5).dp) // Set height to 40% of screen height
//                    ) {
//                        BasicInfo(school = school)
//                        schoolDetailsState.value?.let { schoolDetails ->
//                            DisplayTimetables(allTimetables = schoolDetails.allTimetables)
//                        }
//                    }
//                    // Button to Create Timetable
//                    Button(
//                        onClick = {
//                            val intent = Intent(context, CreateTTActivity::class.java).apply {
//                                putExtra("SCHOOL_ID", id)
//                            }
//                            context.startActivity(intent)
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(48.dp),
//                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.buttonLightHeavy)),
//
//                        ) {
//                        Text(
//                            text = "Create Timetable",
//                            style = MaterialTheme.typography.bodyMedium,
//                            color = Color.White // Set text color to white for contrast
//                        )
//                    }
//                }
//            }
//        }
//    }
//}

