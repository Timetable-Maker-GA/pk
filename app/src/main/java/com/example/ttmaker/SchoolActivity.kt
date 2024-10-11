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
import com.example.ttmaker.presentation.School.SchoolScreen

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
            }
            else schoolId?.let { SchoolScreen(viewModel, it) }
        }
    }
}

