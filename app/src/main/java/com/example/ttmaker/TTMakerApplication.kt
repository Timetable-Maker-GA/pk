package com.example.ttmaker

import android.app.Application
import com.example.ttmaker.data.SchoolDatabase
import com.example.ttmaker.data.SchoolRepository

class TTMakerApplication : Application() {
    lateinit var schoolRepository: SchoolRepository
        private set

    var schoolOrInstitute: String = "School" // Initialize with a default value
        private set // Keep it private if you don't want it to be modified externally

    override fun onCreate() {
        super.onCreate()

        // Initialize the database and repository here
        val database = SchoolDatabase.getInstance(this)
        schoolRepository = SchoolRepository.getInstance(database.schoolDao())
    }
}
