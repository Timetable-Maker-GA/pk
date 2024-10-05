package com.example.ttmaker.pages

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp


//import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPageContent() {

//    var selectedTab by rememberSaveable { mutableStateOf(0) }
//    val context = LocalContext.current
//    var givenInstitute by remember { mutableStateOf<Institute?>(null) }
//    val instituteList =
//        remember { mutableStateListOf<Institute>().apply { addAll(loadInstituteList(context)) } }
//
//    fun addInstitute(newInstitute: Institute) {
//        instituteList.add(newInstitute)
//        saveInstituteList(context, instituteList)
//    }
//
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val nousevar = false
//    // Handle back button press
//    BackHandler {
//        when (selectedTab) {
//            1, 2 -> {
//                selectedTab = 0
//            }
//
//            0 -> {
//                (context as? Activity)?.finish()
//            }
//        }
//    }
//    Column(
//        modifier = Modifier.fillMaxSize()
////        .background(Color.Red)
//    ) {
//        TopAppBar(
//            title = {
//                Text(
//                    text = "Genetic Timetable",
//                    modifier = Modifier.clickable {
//                        selectedTab = 0
//                    }, // Navigate to the landing page on click
//                    color = Color.White // White font color
//                )
//            },
//            modifier = Modifier.fillMaxWidth(),
//            colors = TopAppBarDefaults.mediumTopAppBarColors(
//                containerColor = Color(0xFF87CEEB), // Sky blue background
//                titleContentColor = Color.White // White font color
//            ),
//        )
//        TabRow(
//            selectedTabIndex = selectedTab, modifier = Modifier.padding(0.dp)
//        ) {}
//
//        when (selectedTab) {
//            0 -> Landing(instituteList,
//                toInstituteCreationPage = { selectedTab = 1 },
//                toTimetableCreationPage = { selectedTab = 2 },
//                toTimetableCreationPageWithInstitute = {
//                    selectedTab = 2
//                    givenInstitute = it
//                })
//
//            1 -> CreateInstitute(
//                instituteList,
//                toLandingPage = { selectedTab = 0 },
//                onAddInstitute = { e -> addInstitute(e) },
//                toTimetableCreationPage = { selectedTab = 2 })
//
//            2 -> TimetableCreationPage(
//                instituteList,
//                toLandingPage = { selectedTab = 0 },
//                givenInstitute
//            )
//        }
//    }
}