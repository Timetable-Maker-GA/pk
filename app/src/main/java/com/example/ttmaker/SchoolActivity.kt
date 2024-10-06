package com.example.ttmaker

import  android.view.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.ttmaker.data.SchoolRepository

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
class SchoolActivity : ComponentActivity() {

    private val viewModel: SchoolActivityViewModel by viewModels {
        // Provide the repository to the ViewModel
        SchoolActivityViewModelFactory((application as TTMakerApplication).schoolRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the school ID passed via intent
        val schoolId = intent?.getIntExtra("SCHOOL_ID", -1)
        
        // Fetch the school details if a valid ID is provided
        schoolId?.let { viewModel.fetchSchoolDetails(it) }

        setContent {

            if (schoolId == -1){
                Text(text = "Sorry No Entry in Database")
            }else
            SchoolScreen(viewModel)
        }
    }
}

@Composable
fun SchoolScreen(viewModel: SchoolActivityViewModel) {
    val schoolDetails = viewModel.schoolDetails.collectAsState().value

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
            }

        }
    }
}
