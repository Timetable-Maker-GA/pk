package com.example.ttmaker.model

data class SchoolBasicInfo(
    val name: String,
    val createdAt: Long,
    val timetableCount: Int,
    val id: Int,
    val imageResId: Int? = null // Make it optional by making it nullable)

)