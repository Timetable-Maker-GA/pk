package com.example.ttmaker.presentation.addTimeTable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ntech.ttmaker.R
//import com.example.ttmaker.R

import com.example.ttmaker.classes.Timetable
@Composable
fun DisplayTimetables(allTimetables: List<Timetable>) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val sortedTimetables = allTimetables.sortedByDescending { it.createdAt }

    if (sortedTimetables.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .height(screenHeight * 0.4f)
                .background(Color.White)
        ) {
            items(sortedTimetables) { timetable ->
                TimetableCard(timetable, screenWidth)
            }

        }
    }else{
        Column {
            Text(text = "NO TIMETABLES YET!")
        }
    }
}

@Composable
fun TimetableCard(timetable: Timetable, screenWidth: Dp) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.bgLight)) // Set the background color here

    ) {
        Column(
//            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Class: ${timetable.className}  Sec: ${timetable.section}", style = MaterialTheme.typography.bodyLarge  )
            Spacer(modifier = Modifier.height(8.dp))
            TableDisplay(classTT = timetable.classTT, screenWidth = screenWidth, timetable.DAYS, timetable.HOURS)
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                ) {
                    Text(text = "Subject and ", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Teacher", style = MaterialTheme.typography.bodyMedium)
                }
                timetable.chosenTeachers.forEach { (subject, teacher) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
//                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "$subject  =  ", style = MaterialTheme.typography.bodySmall)
                        Text(text = teacher, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

@Composable
fun TableDisplay(classTT: Array<Array<Pair<String, String>>>, screenWidth: Dp, DAYS: Int, HOURS: Int) {
    val columnWidth = (screenWidth - screenWidth* 0.04f) / (HOURS + 1) // +1 for the day column

    Column {
        // Header row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 3.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(
                text = "Day/P->",
                modifier = Modifier.width(columnWidth),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            for (period in 1..HOURS) {
                Text(
                    text = "$period",
                    modifier = Modifier.width(columnWidth),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
            }
        }

        // Timetable rows
        for (day in classTT.indices) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
//                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${day + 1}",
                    modifier = Modifier.width(columnWidth),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
                for (period in classTT[day].indices) {
                    val (subject, teacher) = classTT[day][period]
                    Text(
                        text = subject,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}