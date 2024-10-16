package com.example.ttmaker.pages

import HorizontalSlider
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.ttmaker.classes.Institute
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.ntech.ttmaker.R
//import com.example.ttmaker.R

import com.example.ttmaker.components.landingpage.InstituteList
import com.example.ttmaker.components.landingpage.TimetableList
import com.example.ttmaker.ui.theme.FontSizes
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Event

@Composable
fun Landing(instituteList: List<Institute>,
            toInstituteCreationPage: ()-> Unit,
            toTimetableCreationPage: ()-> Unit,
            toTimetableCreationPageWithInstitute:
                (givenInstitute: Institute?)-> Unit,
            ) {
    var expanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(colorResource(id = R.color.bgLight)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HorizontalSlider()
            InstituteList(instituteList, toTimetableCreationPageWithInstitute)
            TimetableList(instituteList, toTimetableCreationPageWithInstitute)
        }
        FloatingActionButton(
            onClick = { expanded = true },
            containerColor = colorResource(id = R.color.buttonLightHeavy), // Pink color
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .height(35.dp) // Adjust height as needed
        ) {
            Row(
//                modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = 8.dp),

                ) {
                Text(
                    text = "+",
                    color = Color.White,
                    fontSize = FontSizes.subtitle, // Make the "+" sign bigger
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontWeight = FontWeight.ExtraBold

                )
                Text(
                    text = "ADD",
                    color = Color.White,
                    fontSize = FontSizes.body,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 8.dp),

                    )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 72.dp) // Adjust padding as needed
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .wrapContentWidth()
                    .background(
                        MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(8.dp) // Add padding to the dropdown menu
            ) {
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.AccountBalance, // Icon for "Add Institute"
                                contentDescription = null,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text("Add Institute", style = MaterialTheme.typography.bodyLarge)
                        }
                    },
                    onClick = {
                        expanded = false
                        toInstituteCreationPage()
                    }
                )
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Event, // Icon for "Create Timetable"
                                contentDescription = null,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text("Create Timetable", style = MaterialTheme.typography.bodyLarge)
                        }
                    },
                    onClick = {
                        expanded = false
                        toTimetableCreationPage()
                    }
                )
            }
        }
    }
}
