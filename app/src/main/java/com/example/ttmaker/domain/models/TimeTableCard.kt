package com.example.ttmaker.domain.models

import androidx.compose.ui.graphics.Color

data class TimeTableCard(
    val className: String,
    val session: String,
    val createdOn: String,
    val bgColor: Color,
    val color: Color
)