package com.example.ttmaker.model

data class TeacherInfo(
    val name: String,
    val subjects: List<String>,
    val classLevels: List<ClassLevel>
)
data class ClassLevel(val start: Int, val end: Int)
