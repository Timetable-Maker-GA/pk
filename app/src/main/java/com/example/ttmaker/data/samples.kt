package com.example.ttmaker.data

import com.example.ttmaker.domain.enums.SchoolClass
import com.example.ttmaker.domain.models.School
import com.example.ttmaker.domain.models.Subject
import com.example.ttmaker.domain.models.Teacher

public val sampleSchool = School(
    id = 1,
    name = "St. Mary's Convernt Sr. Sec. School",
    address = "123 Main St, City",
    teachers = listOf(
        Teacher(101, "John Doe", listOf(201)), Teacher(102, "Jane Smith", listOf(123))
    ),
    session = "2023-2024",
    classes = listOf(
        SchoolClass.NURSERY, SchoolClass.GRADE_1, SchoolClass.GRADE_3
    ),
    subjects = listOf(
        Subject(201, "Mathematics"), Subject(202, "Science"), Subject(203, "English")
    )
)