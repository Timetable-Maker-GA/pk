package com.example.ttmaker.domain.models

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState

data class Schedule @OptIn(ExperimentalMaterial3Api::class) constructor(
    var schoolStartTime: TimePickerState? = null,
    var schoolEndTime: TimePickerState? = null,
    var lunchStartTime: TimePickerState? = null,
    var lunchEndTime: TimePickerState? = null,
    var lessonDuration: Int
)