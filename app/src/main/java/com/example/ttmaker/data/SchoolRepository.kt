package com.example.ttmaker.data

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
}