package com.example.ttmaker.data

import androidx.lifecycle.LiveData
import com.example.ttmaker.classes.Timetable
import com.example.ttmaker.model.SchoolBasicInfo

class SchoolRepository private constructor(private val schoolDao: SchoolDao) {

    // Companion object for singleton instance
    companion object {
        @Volatile
        private var INSTANCE: SchoolRepository? = null

        fun getInstance(schoolDao: SchoolDao): SchoolRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SchoolRepository(schoolDao).also { INSTANCE = it }
            }
        }
    }
    suspend fun insertSchool(school: SchoolEntity) {
        schoolDao.insertSchool(school)
    }

    suspend fun getAllSchools(): List<SchoolEntity> {
        return schoolDao.getAllSchools()
    }
    suspend fun getSchoolById(schoolId: Int): SchoolEntity? {
        return schoolDao.getSchoolById(schoolId)
    }
    suspend fun getAllSchoolsBasicInfo(): List<SchoolBasicInfo> {
        return schoolDao.getAllSchoolsBasicInfo()
    }
    // Function to update the timetable and timetable count for a specific school
    suspend fun updateTimetableAndCount(schoolId: Int, newTimetables: List<Timetable>, newCount: Int) {
        schoolDao.updateTimetableAndCount(schoolId, newTimetables, newCount)
    }
}