@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ttmaker.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.ttmaker.presentation.addSchool.components.Form
import com.example.ttmaker.presentation.addSchool.components.TextInput
import com.ntech.ttmaker.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSchoolScreen(
    navController: NavHostController
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
                    color= colorResource(id = R.color.headingLightBlueHeavy)
                ) },
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 }
            )
            Tab(
                text = { Text("Form Input",
                    color= colorResource(id = R.color.headingLightBlueHeavy)
                ) },
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 }
            )

        }

        when (selectedTabIndex) {
            0 -> TextInput(navController)
            1 -> Form()
        }
    }
}