package com.example.ttmaker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ttmaker.classes.Timetable
import com.example.ttmaker.model.SchoolBasicInfo
import com.example.ttmaker.model.SchoolEntity


@Dao
interface SchoolDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: SchoolEntity)

    @Query("SELECT * FROM schools")
    suspend fun getAllSchools(): List<SchoolEntity>

    @Query("SELECT * FROM schools WHERE id = :schoolId")
    suspend fun getSchoolById(schoolId: Int): SchoolEntity?

    @Query("SELECT id, name, createdAt, timetableCount FROM schools")
    suspend  fun getAllSchoolsBasicInfo(): List<SchoolBasicInfo>



    @Query("""
        UPDATE schools 
        SET allTimetables = :newTimetables, timetableCount = :newCount 
        WHERE id = :schoolId
    """)
    suspend fun updateTimetableAndCount(schoolId: Int, newTimetables: List<Timetable>, newCount: Int)

}
