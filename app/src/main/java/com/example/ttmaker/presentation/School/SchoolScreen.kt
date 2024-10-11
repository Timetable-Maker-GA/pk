package com.example.ttmaker.presentation.School

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.ttmaker.CreateTTActivity
import com.example.ttmaker.SchoolActivityViewModel
import com.ntech.ttmaker.R


@Composable
fun SchoolScreen(viewModel: SchoolActivityViewModel, id: Int) {
    val schoolDetails = viewModel.schoolDetails.collectAsState().value
    val context = LocalContext.current


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
                        Column(modifier = Modifier.padding(16.dp)) {
                            // Teacher Information
                            Text(
                                text = "Teachers (${school.teachers.size}):",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            school.teachers.forEach { teacher ->
                                Text(
                                    text = "- ${teacher.name}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Subjects Information
                            Text(
                                text = "Subjects (${school.subjects.size}):",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            school.subjects.forEach { subject ->
                                Text(
                                    text = "- $subject",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Timetable Information
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
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Created At (Formatted Date)
                            val createdAtFormatted = remember {
                                java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale.getDefault())
                                    .format(java.util.Date(school.createdAt))
                            }
                            Text(
                                text = "Created At: $createdAtFormatted",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

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

                } ?: run {
                    // Loading State
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(48.dp)
                            .padding(16.dp)
                    )
                    Text(
                        text = "Loading school details...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}
