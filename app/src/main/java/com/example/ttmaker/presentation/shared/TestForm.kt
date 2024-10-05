//package com.example.ttmaker.presentation.shared
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.ttmaker.data.LocalSchoolRepository
//import com.example.ttmaker.data.SchoolDatabase
//import com.example.ttmaker.data.SchoolRepository
//import com.example.ttmaker.presentation.addSchool.SchoolViewModel
//import com.example.ttmaker.presentation.addSchool.SchoolViewModelFactory
//import kotlinx.coroutines.launch
//
//@Composable
//fun Form(
//    modifier: Modifier
//) {
//    // Initialize repository and ViewModel
//    val context = LocalContext.current
//
//    val repository = LocalSchoolRepository.current
//
//    // Initialize the SchoolViewModel using the stored repository
//    val schoolViewModel: SchoolViewModel = viewModel(factory = SchoolViewModelFactory(repository))
//
//    val scope = rememberCoroutineScope()
//    Column(modifier = Modifier.padding(16.dp)) {
//        TextField(
//            value = schoolViewModel.schoolName.value,
//            onValueChange = { schoolViewModel.onSchoolNameChange(it) },
//            label = { Text("School Name") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        TextField(
//            value = schoolViewModel.periodsPerDay.value.toString(),
//            onValueChange = { schoolViewModel.onPeriodsPerDayChange(it.toIntOrNull() ?: 0) },
//            label = { Text("Periods Per Day") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = {
//            scope.launch {
//                schoolViewModel.insertSchool()
//                schoolViewModel.fetchSchools()
//            }
//        }) {
//            Text("Submit")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Display the list of schools
//        LazyColumn {
//            items(schoolViewModel.schools.value, key = { it.id }) { school ->
//                Text(text = "${school.name} - ${school.periodsPerDay} periods")
//            }
//        }
//    }
//
//    // Fetch schools when the composable is first launched
//    LaunchedEffect(Unit) {
//        schoolViewModel.fetchSchools()
//    }
//
//
//}
