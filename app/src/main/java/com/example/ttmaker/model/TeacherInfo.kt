package com.example.ttmaker.model

data class TeacherInfo(
    val name: String,
    val subjects: List<String>,
    val classLevels: List<Pair<Int, Int>>
)
