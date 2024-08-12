package com.example.ttmaker.domain.models

import com.example.ttmaker.domain.enums.SchoolClass

data class School(
    val id: Int,
    val name: String,
    val address: String?,
    val teachers: List<Teacher>,
    val session: String,
    val classes: List<SchoolClass>,
    val subjects: List<Subject>
)
