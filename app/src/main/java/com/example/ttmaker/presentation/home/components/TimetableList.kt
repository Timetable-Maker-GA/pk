package com.example.ttmaker.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ntech.ttmaker.R
//import com.example.ttmaker.R

import com.example.ttmaker.classes.Institute
import com.example.ttmaker.classes.Timetable

@Composable
fun TimetableCard(
    institute: Institute,
    timetable: Timetable,
    toTimetableCreationPageWithInstitute: (givenInstitute: Institute?) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 0.dp)
            .clickable { toTimetableCreationPageWithInstitute(institute) },
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Class: ${timetable.className} - ${timetable.section}",

                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
//            Text(
//                text = "Created At: ${Date(timetable.createdAt)}",
//                fontSize = 16.sp
//            )
//            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = institute.name,

                color = Color.Gray
            )
        }
    }
}

@Composable
fun TimetableList(
    instituteList: List<Institute>,
    toTimetableCreationPageWithInstitute: (givenInstitute: Institute?) -> Unit
) {
    // Flatten and sort timetables by createdAt in descending order
    val sortedTimetables = instituteList.flatMap { institute ->
        institute.allTimetables.map { timetable ->
            institute to timetable
        }
    }.sortedByDescending { it.second.createdAt }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
//            .verticalScroll(scrollState)
            .background(colorResource(id = R.color.bgLight)),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 8.dp, start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Text(
                color = Color.Gray, text = "Time tables", 
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {

            Text(
                color = colorResource(id = R.color.textLightBluePale), text = "scroll", 
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown, // Use an appropriate icon
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp),
                tint = colorResource(id = R.color.textLightBluePale)
            )
            }

        }
        sortedTimetables.map{

            TimetableCard(it.first, it.second, toTimetableCreationPageWithInstitute)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


//@Composable
//fun InstituteList(instituteList: List<Institute>,
//                  toTimetableCreationPageWithInstitute:
//                      (givenInstitute: Institute?)-> Unit,
//                  ) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorResource(id = R.color.bgLight))) {
//        instituteList.forEach { institute ->
//            InstituteCard(institute, toTimetableCreationPageWithInstitute)
//        }
//    }

