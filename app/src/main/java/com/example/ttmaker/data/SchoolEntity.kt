package com.example.ttmaker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ttmaker.classes.Timetable
import com.example.ttmaker.classes.types.TeacherInfo

@Entity(tableName = "schools")
data class SchoolEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val teachers: List<TeacherInfo>,
    val subjects: List<String>,
    val allTimetables: MutableList<Timetable> = mutableListOf(),
    val DAYS: Int,
    val HOURS: Int,
    val POPULATION_SIZE: Int,
    val GENERATIONS: Int,
    val createdAt: Long
)