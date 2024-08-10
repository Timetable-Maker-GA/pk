package com.example.ttmaker.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.ttmaker.classes.Institute
// typo

import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.ui.res.colorResource
import com.ntech.ttmaker.R
//import com.example.ttmaker.R

import com.example.ttmaker.components.Form
import com.example.ttmaker.components.TextFileInput

@Composable
fun CreateInstitute(
    instituteList: List<Institute>,
    onAddInstitute: (Institute) -> Unit,
    toLandingPage: () -> Unit,
    toTimetableCreationPage: () -> Unit
) {
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
//            .height(200.dp)
            .verticalScroll(scrollState)
    )

    {
        TabRow(selectedTabIndex = selectedTabIndex,

                // Customize TabRow indicator color
                indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = colorResource(id = R.color.buttonLightPale) // Set the color of the indicator to blue
            )
        }) {
            Tab(
                text = { Text("File Input",
                    color= colorResource(id = R.color.textLightBlueHeavy)) },
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 }
            )
            Tab(
                text = { Text("Form Input",
                    color= colorResource(id = R.color.textLightBlueHeavy)) },
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 }
            )   

        }

        when (selectedTabIndex) {
            0 -> TextFileInput(onAddInstitute, toLandingPage)
            1 -> Form(onAddInstitute, toLandingPage)
        }
    }
}