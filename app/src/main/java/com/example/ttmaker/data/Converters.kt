package com.example.ttmaker.data

import androidx.room.TypeConverter
import com.example.ttmaker.classes.Timetable
import com.example.ttmaker.model.TeacherInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun stringToStringList(value: String?): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun stringListToString(list: List<String?>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToTeacherInfoList(value: String?): List<TeacherInfo> {
        val listType = object : TypeToken<List<TeacherInfo>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun teacherInfoListToString(list: List<TeacherInfo?>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToTimetableList(value: String?): List<Timetable> {
        val listType = object : TypeToken<List<Timetable>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun timetableListToString(list: List<Timetable?>?): String {
        return Gson().toJson(list)
    }
}
