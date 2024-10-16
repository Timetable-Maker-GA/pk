package com.example.ttmaker.presentation.addSchool.components


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ttmaker.model.SchoolEntity
import com.example.ttmaker.data.SchoolRepository
import com.example.ttmaker.model.ClassLevel
import com.example.ttmaker.model.TeacherInfo
import kotlinx.coroutines.launch

class TextInputViewModel(private val repository: SchoolRepository) : ViewModel() {
    var txtInput = mutableStateOf("")
        private set
    var temp = mutableStateOf("")
        private set

    fun updateTxtInput(it: String){
        txtInput.value = it
    }
    var toastContent = mutableStateOf("")
        private set

    fun toastShown(){
        toastContent.value = ""
    }
    fun insertSchool() {
        viewModelScope.launch {
            var days = ""
            var hours = ""
            var name = ""
            var subjects = ""
            var teachers: MutableList<TeacherInfo> = mutableListOf()

            var populationSize = 100
            var generations = 100

            // Parse the CSV content
            val lines = txtInput.value
                .trim()
                .split("\n")
                .map { it.trim() }
                .filter { it.isNotEmpty() }

            if (lines.size < 3) {
                toastContent.value = "Input Incomplete"
                return@launch
            }
            // Extract institute details from the first line
            name = lines[0].trim()

            val daysHours = lines[1].split(",")
            if (daysHours.size == 2) {
                days = daysHours[0].trim()
                hours = daysHours[1].trim()
            } else return@launch

            subjects = lines[2].trim()
            var i = 3
            while (i < lines.size && i + 2 < lines.size) {
                // Extract teacher details
                val teacherName = lines[i].trim()
                val subjectsLine = lines[i + 1].trim()
                val classLevelsLine = lines[i + 2].trim()
                i += 3
                val subjectList = subjectsLine.split(",").map { it.trim() }
                val classLevelList = classLevelsLine.split(",").map {
                    val rangeParts = it.split("-")
                    if (rangeParts.size == 2) {
                        val start = rangeParts[0].toIntOrNull()
                        val end = rangeParts[1].toIntOrNull()
                        if (start != null && end != null) {

                            ClassLevel(start, end)  // Replacing Pair with ClassLevel
                        } else {
                            null
                        }
                    } else {
                        null
                    }
                }.filterNotNull()
                if (subjectList.size == classLevelList.size) {
                    teachers +=
                        TeacherInfo(
                            name = teacherName,
                            subjects = subjectList,
                            classLevels = classLevelList
                        )

                } else return@launch
            }
            val school = SchoolEntity(name = name, HOURS = hours.toIntOrNull() ?: 0, DAYS = days.toIntOrNull() ?: 0,
                subjects = subjects.split(",").map { it.trim() },
                GENERATIONS = generations,
                POPULATION_SIZE = populationSize,
                teachers = teachers,
                createdAt = System.currentTimeMillis(),
                timetableCount = 0)
            repository.insertSchool(school)
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
