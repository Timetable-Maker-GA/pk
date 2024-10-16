package com.example.ttmaker.activity

import android.os.Bundle
import androidx.activity.compose.setContent


import androidx.activity.ComponentActivity
import com.example.ttmaker.presentation.addTimeTable.AddTimeTableScreen


class CreateTTActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the school ID passed via intent
        val schoolId = intent?.getIntExtra("SCHOOL_ID", -1)
        setContent {
            if (schoolId != null) {
                AddTimeTableScreen(schoolId)
            }
        }
    }
}