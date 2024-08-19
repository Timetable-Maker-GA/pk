package com.example.ttmaker.presentation.addSchool.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ttmaker.domain.models.Schedule
import com.example.ui.theme.manrope


@ExperimentalMaterial3Api
@Composable
fun AddSchedule() {
    val focusRequester = remember { FocusRequester() }

    var schedule by remember {
        mutableStateOf(
            Schedule(
                schoolStartTime = null,
                schoolEndTime = null,
                lunchStartTime = null,
                lunchEndTime = null,
                lessonDuration = 15,
            )
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "School Start Time",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontFamily = manrope
            )
            Spacer(modifier = Modifier.height(8.dp))
            PickTime(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp), focusRequester
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "School End Time",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontFamily = manrope
            )
            Spacer(modifier = Modifier.height(8.dp))
            PickTime(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp), focusRequester
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Lunch Start Time",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontFamily = manrope
            )
            Spacer(modifier = Modifier.height(8.dp))
            PickTime(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp), focusRequester
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Lunch End Time",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontFamily = manrope
            )
            Spacer(modifier = Modifier.height(8.dp))
            PickTime(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp), focusRequester
            )
        }
    }
}
