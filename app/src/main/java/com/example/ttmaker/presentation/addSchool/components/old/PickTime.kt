package com.example.ttmaker.presentation.addSchool.components.old

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickTime(

    modifier: Modifier = Modifier, focusRequester: FocusRequester
) {
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    val myState = rememberTimePickerState(0, 0)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        TimeInput(
            state = myState,
            modifier = Modifier
                .scale(1f)
                .focusRequester(focusRequester = focusRequester),
            colors = TimePickerDefaults.colors().copy(
                containerColor = Color.White,
                timeSelectorSelectedContainerColor = Color(0xFFdbeafe),
                timeSelectorUnselectedContainerColor = Color.White,
                periodSelectorSelectedContainerColor = Color(0xFFdbeafe),
                periodSelectorUnselectedContainerColor = Color.White,
            )
        )
    }

}