package com.example.ttmaker.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
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
                TimetableCard(timetable)
            }
            item{
                TeacherOverlaps()
            }
        }
    }else{
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.2f),
                    horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No timetables created yet. Get started now!",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun TeacherOverlaps(){

}
@Composable
fun TimetableInfo(timetable: Timetable) {
    // Calculate the count of each subject in the timetable
    val subjectCount = rememberSaveable {
        mutableMapOf<String, Int>().apply {
            for (day in 0 until timetable.DAYS) {
                for (hour in 0 until timetable.HOURS) {
                    val subject = timetable.classTT[day][hour].first
                    this[subject] = getOrDefault(subject, 0) + 1
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp) // Add padding around the entire component
    ) {
        // Section title
        Text(
            text = "Timetable Information",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp) // Space below title
        )

// Table Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Subject",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(2f)
            )
            Text(
                text = "Teacher",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(2f)
            )
            Text(
                text = "Periods / Week",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(2f),
                textAlign = TextAlign.End
            )
        }


        // Subject rows
        timetable.chosenTeachers.forEach { (subject, teacher) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = subject,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(2f)
                )
                Text(
                    text = teacher,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(2f)
                )
                Text(
                    text = "${subjectCount[subject] ?: 0} / ${timetable.subPeriodsPerWeek[subject] ?: 0}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}


@Composable
fun TableDisplay(classTT: Array<Array<Pair<String, String>>>, screenWidth: Dp, DAYS: Int, HOURS: Int) {
    val columnWidth = screenWidth / (HOURS + 1) // +1 for the day column

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
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            for (period in 1..HOURS) {
                Text(
                    text = "$period",
                    modifier = Modifier.width(columnWidth),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
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
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                for (period in classTT[day].indices) {
                    val (subject, teacher) = classTT[day][period]
//                    Text(
//                        text = subject,
//                        style = MaterialTheme.typography.bodySmall,
//                        textAlign = TextAlign.Center
//                    )
                    Text(
                        text = subject,
                        modifier = Modifier.width(columnWidth),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.width(2.dp))
            }
        }
    }
}

@Composable
fun TimetableCard(timetable: Timetable) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.bgLight)) // Set the background color here

    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Class: ${timetable.className}  Sec: ${timetable.section}", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(8.dp))
            TableDisplay(classTT = timetable.classTT, screenWidth = screenWidth, timetable.DAYS, timetable.HOURS)
            Spacer(modifier = Modifier.height(8.dp))
            TimetableInfo(timetable)
        }
    }
}