package com.example.ttmaker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ttmaker.data.SchoolRepository
import com.example.ttmaker.data.SchoolEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SchoolActivityViewModel(private val repository: SchoolRepository) : ViewModel() {

    private val _schoolDetails = MutableStateFlow<SchoolEntity?>(null)
    val schoolDetails: StateFlow<SchoolEntity?> = _schoolDetails

    fun fetchSchoolDetails(schoolId: Int) {
        viewModelScope.launch {
            _schoolDetails.value = repository.getSchoolById(schoolId) // Ensure this method is implemented in your repository
        }
    }
}

class SchoolActivityViewModelFactory(
    private val repository: SchoolRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SchoolActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SchoolActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}