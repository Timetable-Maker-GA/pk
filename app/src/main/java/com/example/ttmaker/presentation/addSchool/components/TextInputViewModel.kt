package com.example.ttmaker.presentation.addSchool.components


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ttmaker.data.SchoolEntity
import com.example.ttmaker.data.SchoolRepository
import kotlinx.coroutines.launch

class TextInputViewModel(private val repository: SchoolRepository) : ViewModel() {

    var txtInput = mutableStateOf("")
        private set
    var temp = mutableStateOf("")
        private set

    fun updateTxtInput(it: String){
        txtInput.value = it
    }

    fun insertSchool() {
        viewModelScope.launch {
            // Split the input into lines
            val lines = txtInput.value.lines().map { it.trim() }

            if (lines.size >= 2) {
                val schoolName = lines[0] // First line: School Name
                val hours = lines[1].toIntOrNull() ?: 0 // Second line: Periods Per Day, default to 0 if invalid

                val school = SchoolEntity(name = schoolName, HOURS = hours, createdAt = System.currentTimeMillis())
                repository.insertSchool(school)
                temp.value = "FILLING"
            } else {
                println("Invalid input format. Please enter the school name on the first line and periods on the second.")
            }

//            fetchSchools() // Update the list of schools after insertion
        }
    }
}

class TextInputViewModelFactory(private val repository: SchoolRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TextInputViewModel::class.java)) {
            return TextInputViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class in TxtInputVM")
    }
}
