package com.example.ttmaker.activity

import android.os.Bundle
import androidx.activity.compose.setContent


import androidx.activity.ComponentActivity
import com.example.ttmaker.presentation.AddSchoolScreen


class CreateSchoolActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                AddSchoolScreen()
        }
    }
}