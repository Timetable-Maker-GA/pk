package com.example.ttmaker

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import  android.view.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text


import androidx.activity.ComponentActivity
import com.example.ttmaker.presentation.addTimeTable.AddTimeTableScreen


class CreateTTActivity : ComponentActivity() {

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
            if (schoolId != null) {
                AddTimeTableScreen(schoolId)
            }
        }
    }
}