package com.example.ttmaker.presentation.addSchool.components


import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ttmaker.data.SchoolEntity
import com.example.ttmaker.data.SchoolRepository
import com.example.ttmaker.model.ClassLevel
import com.example.ttmaker.model.TeacherInfo
import kotlinx.coroutines.launch

class FormViewModel(private val repository: SchoolRepository) : ViewModel() {

    var name = mutableStateOf("")
        private set
    var subjects = mutableStateOf("")
        private set
    var days = mutableStateOf("")
        private set
    var hours = mutableStateOf("")
        private set

    var populationSize = mutableStateOf(100)
        private set

    var generations = mutableStateOf(100)
        private set

    var teacherName = mutableStateOf("")
        private set

    var teachersSub = mutableStateOf("")
        private set

    var classes = mutableStateOf("")
        private set

    var teachers = mutableStateOf<List<TeacherInfo>>(emptyList())
        private set
    fun updateName(newName: String) {
        name.value = newName
    }

    fun updateSubjects(newSubjects: String) {
        subjects.value = newSubjects
    }

    fun updateDays(newDays: String) {
        days.value = newDays
    }

    fun updateHours(newHours: String) {
        hours.value = newHours
    }

    fun updatePopulationSize(newSize: Int) {
        populationSize.value = newSize
    }

    fun updateGenerations(newCount: Int) {
        generations.value = newCount
    }

    fun updateTeacherName(newName: String) {
        teacherName.value = newName
    }

    fun updateTeachersSub(newSub: String) {
        teachersSub.value = newSub
    }

    fun updateClasses(newClasses: String) {
        classes.value = newClasses
    }

    fun addTeacher(newTeacher: TeacherInfo) {
        teachers.value = teachers.value + newTeacher
    }
    fun updateTeachers(newTeachers: List<TeacherInfo>) {
        teachers.value = newTeachers
    }
    fun insertSchool() {
        viewModelScope.launch {
            // Create a SchoolEntity instance with the updated parameters
            val school = SchoolEntity(
                name = name.value,
                teachers = teachers.value, // Make sure this is already of type List<TeacherInfo>
                subjects = subjects.value.split(",").map { it.trim() }, // Split and trim subjects
                DAYS = days.value.toIntOrNull() ?: 0, // Convert to Int, default to 0
                HOURS = hours.value.toIntOrNull() ?: 0, // Convert to Int, default to 0
                POPULATION_SIZE = populationSize.value, // Access the mutable state
                GENERATIONS = generations.value, // Access the mutable state
                createdAt = System.currentTimeMillis(), // Get the current timestamp
                timetableCount = 0 // Initialize to 0
            )

            // Insert the school into the repository
            repository.insertSchool(school)
        }
    }
}

class FormViewModelFactory(private val repository: SchoolRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormViewModel::class.java)) {
            return FormViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class in TxtInputVM")
    }
}