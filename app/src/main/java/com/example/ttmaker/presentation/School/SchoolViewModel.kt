package com.example.ttmaker.presentation.School

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ttmaker.data.SchoolRepository
import com.example.ttmaker.model.SchoolEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SchoolViewModel(private val repository: SchoolRepository) : ViewModel() {

    private val _schoolDetails = MutableStateFlow<SchoolEntity?>(null)
    val schoolDetails: StateFlow<SchoolEntity?> = _schoolDetails

    fun fetchSchoolDetails(schoolId: Int) {
        viewModelScope.launch {
            _schoolDetails.value = repository.getSchoolById(schoolId) // Ensure this method is implemented in your repository
        }
    }
}

class SchoolViewModelFactory(
    private val repository: SchoolRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SchoolViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SchoolViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}