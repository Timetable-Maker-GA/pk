package com.example.ttmaker.presentation.home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ttmaker.data.SchoolEntity
import com.example.ttmaker.data.SchoolRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SchoolRepository) : ViewModel() {

    var schoolList = mutableStateOf<List<SchoolEntity>>(emptyList())
        private set

    init {
        fetchSchools()  // Call the function here to load the data
    }
    private fun fetchSchools() {
        viewModelScope.launch {
            schoolList.value = repository.getAllSchools() // Fetch the schools from the repository
        }
    }
}

class HomeViewModelFactory(private val repository: SchoolRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class in HomeInputVM")
    }
}
