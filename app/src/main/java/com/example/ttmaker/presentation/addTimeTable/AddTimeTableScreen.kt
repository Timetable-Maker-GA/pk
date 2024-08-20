package com.example.ttmaker.presentation.addTimeTable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ttmaker.ManropeFontFamily
import com.example.ttmaker.util.classList
import com.example.ttmaker.util.schoolData
import com.example.ttmaker.util.sectionList
import com.example.ttmaker.util.sessionList
import com.example.ui.theme.manrope

@Composable
fun AddTimeTableScreen(
    navController: NavHostController
) {
    Scaffold(
        containerColor = Color(0xFFF2F3F4),
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            TimeTableForm(modifier = Modifier.padding(innerPadding))
        }
    }
}


data class ClassTimeTable(
    val school: String, val classId: String, val section: String, val className: String
)

@Composable
fun TimeTableForm(
    modifier: Modifier
) {
    var expandedSchoolList by remember { mutableStateOf(false) }
    var expandedClassList by remember { mutableStateOf(false) }
    var expandedSectionList by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var schoolTimeTable = remember {
        mutableStateOf(
            ClassTimeTable(
                school = schoolData.name,
                classId = classList[0].name,
                section = sectionList[0],
                className = classList[0].className
            )
        )
    }

    Column(
        modifier = Modifier.scrollable(
            orientation = Orientation.Vertical, state = rememberScrollState()
        )
    ) {

        Column {
            Text(
                text = "Select School",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontFamily = manrope
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopStart)
                    .clip(RoundedCornerShape(16.dp))

            ) {

                Text(text = schoolTimeTable.value.school,
                    modifier = Modifier
                        .clickable { expandedSchoolList = true }
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(16.dp),
                    fontSize = 16.sp,
                    fontFamily = ManropeFontFamily,
                    color = Color.Black)
                DropdownMenu(
                    expanded = expandedSchoolList,
                    onDismissRequest = { expandedSchoolList = false },
                    scrollState = scrollState,
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(16.dp)
                        )
                        .background(Color.White),

                    ) {
                    sessionList.forEach { it ->
                        DropdownMenuItem(text = {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Black,
                                fontFamily = manrope
                            )
                        }, onClick = {
                            schoolTimeTable.value = schoolTimeTable.value.copy(school = it)
                            expandedSchoolList = false
                        })
                    }

                }
                LaunchedEffect(expandedSchoolList) {
                    if (expandedSchoolList) {
                        scrollState.scrollTo(scrollState.maxValue)
                    }
                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Column(
                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    text = "Select Class",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    fontFamily = manrope
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .wrapContentSize(Alignment.TopStart)
                        .clip(RoundedCornerShape(16.dp))

                ) {

                    Text(text = schoolTimeTable.value.className,
                        modifier = Modifier
                            .clickable { expandedClassList = true }
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .fillMaxWidth()
                            .padding(16.dp),
                        fontSize = 16.sp,
                        fontFamily = ManropeFontFamily,
                        color = Color.Black)
                    DropdownMenu(
                        expanded = expandedClassList,
                        onDismissRequest = { expandedClassList = false },
                        scrollState = scrollState,
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(16.dp)
                            )
                            .background(Color.White),

                        ) {
                        classList.forEach { it ->
                            DropdownMenuItem(text = {
                                Text(
                                    text = it.className,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.Black,
                                    fontFamily = manrope
                                )
                            }, onClick = {
                                schoolTimeTable.value =
                                    schoolTimeTable.value.copy(className = it.className)

                                expandedClassList = false
                            })
                        }

                    }
                    LaunchedEffect(expandedClassList) {
                        if (expandedClassList) {
                            scrollState.scrollTo(scrollState.maxValue)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    text = "Select Section",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    fontFamily = manrope
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .wrapContentSize(Alignment.TopStart)
                        .clip(RoundedCornerShape(16.dp))

                ) {

                    Text(text = schoolTimeTable.value.section,
                        modifier = Modifier
                            .clickable { expandedSectionList = true }
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .fillMaxWidth()
                            .padding(16.dp),
                        fontSize = 16.sp,
                        fontFamily = ManropeFontFamily,
                        color = Color.Black)
                    DropdownMenu(
                        expanded = expandedSectionList,
                        onDismissRequest = { expandedSectionList = false },
                        scrollState = scrollState,
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(16.dp)
                            )
                            .background(Color.White),

                        ) {
                        sectionList.forEach { it ->
                            DropdownMenuItem(text = {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.Black,
                                    fontFamily = manrope
                                )
                            }, onClick = {
                                schoolTimeTable.value = schoolTimeTable.value.copy(section = it)
                                expandedSectionList = false
                            })
                        }

                    }
                    LaunchedEffect(expandedSectionList) {
                        if (expandedSectionList) {
                            scrollState.scrollTo(scrollState.maxValue)
                        }
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3165FF), contentColor = Color.White
            ), shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Add",
                fontFamily = manrope,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

    }
}