package com.example.ttmaker.presentation.School

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.ttmaker.CreateTTActivity
import com.example.ttmaker.SchoolActivityViewModel


@Composable
fun SchoolScreen(viewModel: SchoolActivityViewModel, id: Int) {
    val schoolDetails = viewModel.schoolDetails.collectAsState().value
    val context = LocalContext.current

    // Display the school information
    MaterialTheme {
        Surface {
            Column {
                schoolDetails?.let {
                    Text("School Name: ${it.name}")
                    // Display more school details as needed
                    // For example:
                    Text("Teachers: ${it.teachers.joinToString { teacher -> teacher.name }}")
                    Text("Subjects: ${it.subjects.joinToString()}")
                    // Continue displaying other properties as needed
                } ?: run {
                    Text("Loading school details...")
                }
                Button(onClick = {
                    // Start the activity and pass the selected school to CreateTTActivity
                    val intent = Intent(context, CreateTTActivity::class.java).apply {
                        putExtra("SCHOOL_ID", id)  // Assuming 'id' is unique for each school
                    }
                    context.startActivity(intent)
                }) {
                    Text(text = "Create Timetable")
                }
            }

        }
    }
}