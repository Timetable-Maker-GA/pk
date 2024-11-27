package com.example.ttmaker.presentation.home.components


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ttmaker.data.SchoolRepository
import com.example.ttmaker.model.SchoolBasicInfo
import kotlinx.coroutines.launch

class TimetableListViewModel(private val repository: SchoolRepository) : ViewModel() {


    // Mutable state to hold the list of schools
    var schoolList = mutableStateOf<List<SchoolBasicInfo>>(emptyList())
        private set

    init {
        fetchSchools()
    }

    private fun fetchSchools() {
        viewModelScope.launch {
            // Fetch the data from the repository
            val schools = repository.getAllSchoolsBasicInfo()
            schoolList.value = schools // Update state
        }
    }
}

class TimetableListViewModelFactory(private val repository: SchoolRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimetableListViewModel::class.java)) {
            return TimetableListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class in TimetableListViewModel")
    }
}
