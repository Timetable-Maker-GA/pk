package com.example.ttmaker.domain


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ttmaker.data.SchoolEntity
import com.example.ttmaker.data.SchoolRepository
import kotlinx.coroutines.launch

class SchoolViewModel(private val repository: SchoolRepository) : ViewModel() {

    var schoolName = mutableStateOf("")
        private set

    var periodsPerDay = mutableStateOf(0)
        private set

    fun onSchoolNameChange(newName: String) {
        schoolName.value = newName
    }

    fun onPeriodsPerDayChange(newPeriods: Int) {
        periodsPerDay.value = newPeriods
    }
    var schools = mutableStateOf<List<SchoolEntity>>(emptyList())
        private set

    fun insertSchool() {
        viewModelScope.launch {
            val school = SchoolEntity(name = schoolName.value, periodsPerDay = periodsPerDay.value)
            repository.insertSchool(school)
            // Optionally clear inputs here
            schoolName.value = ""
            periodsPerDay.value = 0
            fetchSchools() // Update the list of schools after insertion
        }
    }

    fun fetchSchools() {
        viewModelScope.launch {
            val fetchedSchools = repository.getAllSchools()
            schools.value = fetchedSchools
        }
    }
}

class SchoolViewModelFactory(private val repository: SchoolRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SchoolViewModel::class.java)) {
            return SchoolViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
