package com.example.ttmaker.data

class SchoolRepository(private val schoolDao: SchoolDao) {

    suspend fun insertSchool(school: SchoolEntity) {
        schoolDao.insertSchool(school)
    }

    suspend fun getAllSchools(): List<SchoolEntity> {
        return schoolDao.getAllSchools()
    }
}
