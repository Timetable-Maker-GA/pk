package com.example.ttmaker.domain.models

import com.example.ttmaker.domain.enums.SchoolClass

data class Teacher(
    val id : Int,
    val name : String,
    val subjects : List<Int>? = null,
    val classes : List<String>? = null,
    val classTeacher: SchoolClass? = null,
    val isClassTeacher: Boolean? = null
)
