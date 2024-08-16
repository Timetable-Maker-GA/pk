package com.example.ttmaker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schools")
data class SchoolEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val periodsPerDay: Int
)