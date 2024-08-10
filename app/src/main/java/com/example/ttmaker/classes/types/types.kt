package com.example.ttmaker.classes.types


data class TeacherInfo(
    val name: String,
    val subjects: List<String>,
    val classLevels: List<Pair<Int, Int>>
)

data class Slide(
    val title: String,
    val description: String,
    val img: String
)