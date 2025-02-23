package com.example.ttmaker.presentation.School

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import com.example.ttmaker.presentation.shared.TimetableCard
import com.ntech.ttmaker.R
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun SchoolScreen(id: Int) {
    val context = LocalContext.current
    val app = context.applicationContext as TTMakerApplication
    val vm: SchoolViewModel = viewModel(factory = SchoolViewModelFactory(app.schoolRepository))

    LaunchedEffect(id) {
        vm.fetchSchoolDetails(id)
    }
    val schoolDetails = vm.schoolDetails.collectAsState().value
    val configuration = LocalConfiguration.current
    val vh = configuration.screenHeightDp.dp

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                schoolDetails?.let { school ->
                    // School Header
                    Text(
                        text = school.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = colorResource(id = R.color.headingLightBlueHeavy),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

//                    LazyColumn(
//                        modifier = Modifier.weight(1f).height(vh * 0.5f)
//                    ) {
//                        item {
//                            // School Basic Info Card
//                            SchoolBasicInfoCard(school)
//                        }
//                    }
                    // Use LazyColumn for scrollable content (Basic Info & Timetables)
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        item {
                            // School Basic Info Card
                            SchoolBasicInfoCard(school)
                        }
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        // Display Timetables (sorted by createdAt)
                        val sortedTimetables = school.allTimetables.sortedByDescending { it.createdAt }
                        items(sortedTimetables) { timetable ->
                            TimetableCard(timetable)
                        }
                    }

                    // Bottom Section: Button to Create New Timetable
                    Button(
                        onClick = {
                            val intent = Intent(context, CreateTTActivity::class.java).apply {
                                putExtra("SCHOOL_ID", id)
                            }
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.buttonLightHeavy)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Create Timetable",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SchoolBasicInfoCard(school: SchoolEntity) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.bgDark))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Teacher Section
            Text(
                text = "Teachers & Subjects",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.headingLightBlueHeavy)
            )
            Spacer(modifier = Modifier.height(8.dp))
            // List all teachers with details:
            school.teachers.forEach { teacher ->
                TeacherInfoItem(teacher)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))
            // School Summary Info
            Text(
                text = "School Timetable Info",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.headingLightBlueHeavy)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Total Timetables: ${school.timetableCount}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Days: ${school.DAYS}, Hours: ${school.HOURS}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Population: ${school.POPULATION_SIZE}, Generations: ${school.GENERATIONS}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            val createdAtFormatted = remember {
                SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(school.createdAt)
            }
            Text(
                text = "Created At: $createdAtFormatted",
                style = MaterialTheme.typography.bodySmall,
//                color = colorResource(id = R.color.headingLightBlueHeavy)
            )
        }
    }
}


@Composable
fun TeacherInfoItem(teacher: com.example.ttmaker.model.TeacherInfo) {
    // Card or simple Column layout for teacher info
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.2f))
            .padding(8.dp)
    ) {
        // Teacher Name
        Text(
            text = teacher.name,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        // Subjects they teach
        Text(
            text = "Subjects: ${teacher.subjects.joinToString(", ")}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        // Class Levels
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Class Levels: ",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
//                        color = colorResource(id = R.color.headingLightBlueHeavy)
            )
            // Each class level displayed as "start - end"
            val classLevelsText = teacher.classLevels.joinToString { "${it.start}-${it.end}" }
            Text(
                text = classLevelsText,
                style = MaterialTheme.typography.bodyMedium,
//                color = colorResource(id = R.color.headingLightBluePale)
            )
        }
    }
}