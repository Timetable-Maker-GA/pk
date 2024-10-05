package com.example.ttmaker.data

import androidx.compose.runtime.staticCompositionLocalOf

// Create a data class to hold both the repository and the school or institute name
data class SchoolContext(
    val schoolRepository: SchoolRepository,
    val schoolOrInstitute: String
)

// Create a CompositionLocal for the SchoolContext
val LocalSchoolContext = staticCompositionLocalOf<SchoolContext> {
    error("No SchoolContext provided")
}