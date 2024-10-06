package com.example.ttmaker.components

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.UUID


// Function to get the SharedPreferences instance
//fun getSharedPreferences(context: Context): SharedPreferences {
//    return context.getSharedPreferences("InstitutePrefs1", Context.MODE_PRIVATE)
//}
//
//// Function to save the institute list to SharedPreferences
//fun saveInstituteList(context: Context, instituteList: List<Institute>) {
//    val sharedPreferences = getSharedPreferences(context)
//    val editor = sharedPreferences.edit()
//    val gson = Gson()
//    val json = gson.toJson(instituteList)
//    editor.putString("instituteList", json)
//    editor.apply()
//}

// Function to load the institute list from SharedPreferences
//fun loadInstituteList(context: Context): List<Institute> {
//    val sharedPreferences = getSharedPreferences(context)
//    val gson = Gson()
//    val json = sharedPreferences.getString("instituteList", null)
//    val type = object : TypeToken<List<Institute>>() {}.type
//    return if (json != null) {
//        gson.fromJson(json, type)
//    } else {
//        emptyList()  // Return an empty list if no data is found
//    }
//}
//fun getSampleInstitute(): Institute {
//    val teachers = listOf(
//        TeacherInfo(
//            name = "Smith",
//            subjects = listOf("Math", "Science"),
//            classLevels = listOf(8 to 10, 11 to 12)
//        ),
//        TeacherInfo(
//            name = "Johnson",
//            subjects = listOf("English"),
//            classLevels = listOf(8 to 10)
//        ),
//        TeacherInfo(
//            name = "Brown",
//            subjects = listOf("History", "Geo"),
//            classLevels = listOf(8 to 9, 10 to 11)
//        ),
//        TeacherInfo(
//            name = "Lee",
//            subjects = listOf("Math", "English"),
//            classLevels = listOf(9 to 11, 11 to 12)
//        ),
//        TeacherInfo(
//            name = "Adams",
//            subjects = listOf("Science"),
//            classLevels = listOf(8 to 9)
//        )
//    )
//
//
//    return Institute(
//        name = "Sample High School",
//        teachers = teachers,
//        subjects = listOf("Math", "Science", "English", "History", "Geo"),
//        DAYS = 4,
//        HOURS = 6,
//        POPULATION_SIZE = 100,  // Sample value
//        GENERATIONS = 1000,    // Sample value
//        id = UUID.randomUUID().toString(),
//        createdAt = System.currentTimeMillis()
//    )
//}
//
//fun loadInstituteList(context: Context): List<Institute> {
//    val sharedPreferences = getSharedPreferences(context)
//    val gson = Gson()
//    val json = sharedPreferences.getString("instituteList", null)
//    val type = object : TypeToken<List<Institute>>() {}.type
//    val institutes = if (json != null) {
//        gson.fromJson<List<Institute>>(json, type)
//    } else {
//        emptyList()
//    }
//
//    // If the list is empty, add a sample institute
//    if (institutes.isEmpty()) {
//        val sampleInstitute = getSampleInstitute()
//        val updatedInstitutes = institutes.toMutableList().apply {
//            add(sampleInstitute)
//        }
//        saveInstituteList(context, updatedInstitutes)
//        return updatedInstitutes
//    }
//
//    return institutes
//}
//fun updateInstitute(context: Context, updatedInstitute: Institute) {
//    val institutes = loadInstituteList(context).toMutableList()
//    val index = institutes.indexOfFirst { it.id == updatedInstitute.id }
//    if (index != -1) {
//        institutes[index] = updatedInstitute
//        saveInstituteList(context, institutes)
//    }
//}


