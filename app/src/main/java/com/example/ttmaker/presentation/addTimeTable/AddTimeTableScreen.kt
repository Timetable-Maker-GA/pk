package com.example.ttmaker.presentation.addTimeTable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ttmaker.ManropeFontFamily
import com.example.ui.theme.manrope
import com.ntech.ttmaker.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTimeTableScreen(
    navController: NavHostController
) {
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = Color.Transparent
            ),
            title = { },
            navigationIcon = {
                Box(modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable {
                        // Navigate to AddSchoolScreen
                    }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                navController.popBackStack()
                            }
                            .clip(CircleShape)
                            .padding(16.dp)
                            .clip(CircleShape),
                    )
                }

            },
        )
    }, containerColor = Color(0xFFF2F3F4), content = { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            TimeTableForm(modifier = Modifier.padding(innerPadding), navController)
        }

    }

    )
}


data class ClassTimeTable(
    val school: String, val classId: String, val section: String, val className: String
)

@Composable
fun TimeTableForm(
    modifier: Modifier, navController: NavHostController
) {
//                    DropdownMenu(
//                        expanded = expandedClassList,
//                        onDismissRequest = { expandedClassList = false },
//                        scrollState = scrollState,
//                        modifier = Modifier
//                            .clip(
//                                RoundedCornerShape(16.dp)
//                            )
//                            .background(Color.White),
//
//                        ) {
//                        classList.forEach { it ->
//                            DropdownMenuItem(text = {
//                                Text(
//                                    text = it.className,
//                                    style = MaterialTheme.typography.titleMedium,
//                                    color = Color.Black,
//                                    fontFamily = manrope
//                                )
//                            }, onClick = {
//                                schoolTimeTable.value =
//                                    schoolTimeTable.value.copy(className = it.className)
//
//                                expandedClassList = false
//                            })
//                        }
//
//                    }
//                    LaunchedEffect(expandedClassList) {
//                        if (expandedClassList) {
//                            scrollState.scrollTo(scrollState.maxValue)
//                        }
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.width(16.dp))
//            Column(
//                modifier = Modifier
//                    .weight(0.5f)
//                    .height(100.dp)
//            ) {
//                Text(
//                    text = "Select Section",
//                    style = MaterialTheme.typography.titleMedium,
//                    color = Color.Black,
//                    fontFamily = manrope
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                Box(
//                    modifier = Modifier
//                        .weight(0.5f)
//                        .wrapContentSize(Alignment.TopStart)
//                        .clip(RoundedCornerShape(16.dp))
//
//                ) {
//
//                    Text(text = schoolTimeTable.value.section,
//                        modifier = Modifier
//                            .clickable { expandedSectionList = true }
//                            .clip(RoundedCornerShape(16.dp))
//                            .background(Color.White)
//                            .fillMaxWidth()
//                            .padding(16.dp),
//                        fontSize = 16.sp,
//                        fontFamily = ManropeFontFamily,
//                        color = Color.Black)
//                    DropdownMenu(
//                        expanded = expandedSectionList,
//                        onDismissRequest = { expandedSectionList = false },
//                        scrollState = scrollState,
//                        modifier = Modifier
//                            .clip(
//                                RoundedCornerShape(16.dp)
//                            )
//                            .background(Color.White),
//
//                        ) {
//                        sectionList.forEach { it ->
//                            DropdownMenuItem(text = {
//                                Text(
//                                    text = it,
//                                    style = MaterialTheme.typography.titleMedium,
//                                    color = Color.Black,
//                                    fontFamily = manrope
//                                )
//                            }, onClick = {
//                                schoolTimeTable.value = schoolTimeTable.value.copy(section = it)
//                                expandedSectionList = false
//                            })
//                        }
//
//                    }
//                    LaunchedEffect(expandedSectionList) {
//                        if (expandedSectionList) {
//                            scrollState.scrollTo(scrollState.maxValue)
//                        }
//                    }
//                }
//            }
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Row(
//            modifier = Modifier
//                .wrapContentHeight()
//                .fillMaxWidth()
//                .clip(RoundedCornerShape(16.dp))
//                .background(Color.White)
//                .clickable {
//                    navController.navigate("select_subject")
//                }
//                .padding(16.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(
//                text = "Select subjects",
//                style = MaterialTheme.typography.bodyLarge,
//                color = Color.Black,
//                fontFamily = manrope
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Icon(painter = painterResource(id = R.drawable.forward), contentDescription = null)
//        }
//        Spacer(modifier = Modifier.height(24.dp))
//        Button(
//            onClick = {}, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFF3165FF), contentColor = Color.White
//            ), shape = RoundedCornerShape(16.dp)
//        ) {
//            Text(
//                modifier = Modifier.padding(8.dp),
//                text = "Add",
//                fontFamily = manrope,
//                style = MaterialTheme.typography.titleMedium,
//
//                )
//        }
//
//
//    }
}