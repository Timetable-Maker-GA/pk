package com.example.ttmaker.util

import com.example.ttmaker.domain.enums.SchoolClass
import com.example.ttmaker.domain.models.School
import com.example.ttmaker.domain.models.Subject
import com.example.ttmaker.domain.models.Teacher

val sessionList = listOf(
    "2020-2021",
    "2021-2022",
    "2022-2023",
    "2023-2024",
    "2024-2025",
    "2025-2026",
)
val sectionList = listOf(
    "A",
    "B",
    "C",
    "D",
    "E",
    "F",
)
val classList = listOf(
    SchoolClass.NURSERY,
    SchoolClass.LKG,
    SchoolClass.UKG,
    SchoolClass.GRADE_1,
    SchoolClass.GRADE_2,
    SchoolClass.GRADE_3,
    SchoolClass.GRADE_4,
    SchoolClass.GRADE_5,
    SchoolClass.GRADE_6,
    SchoolClass.GRADE_7,
    SchoolClass.GRADE_8,
    SchoolClass.GRADE_9,
    SchoolClass.GRADE_10,
    SchoolClass.GRADE_11,
    SchoolClass.GRADE_12,
)

val teacher1 = Teacher(id = 1, name = "John Doe")
val teacher2 = Teacher(id = 2, name = "Jane Smith")

val class1 = SchoolClass.GRADE_12
val class2 = SchoolClass.GRADE_11

val subject1 = Subject(id = 1, name = "Algebra")
val subject2 = Subject(id = 2, name = "Biology")

// Create a mutable school instance
val schoolData = School(
    id = 1,
    name = "Springfield High",
    address = "742 Evergreen Terrace",
    teachers = mutableListOf(teacher1, teacher2),
    session = "2024-2025",
    classes = mutableListOf(class1, class2),
    subjects = mutableListOf(subject1, subject2)
)
