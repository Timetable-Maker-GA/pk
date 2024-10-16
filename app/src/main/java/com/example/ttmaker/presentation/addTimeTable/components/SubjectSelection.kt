package com.example.ttmaker.presentation.addTimeTable.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.util.Objects

import com.example.ttmaker.model.SchoolEntity
import com.ntech.ttmaker.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectSelection(
    selectedInstitute: SchoolEntity, className: String, subPeriodsPerWeek: Map<String, Int>,
    updateSubPeriods: (subject: String, periods: Int) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val visibleSubjects: MutableSet<String> = mutableSetOf()
    for (teacher in selectedInstitute.teachers) {
        teacher.subjects.zip(teacher.classLevels).forEach { (subject, classLevel) ->
            if ((className.toIntOrNull() ?: -1) in classLevel.start..classLevel.end) {
                visibleSubjects.add(subject)
            }
        }
    }

    if (Objects.isNull(className.toIntOrNull()) ||
        visibleSubjects.size == 0) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight*0.1f)
                ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (visibleSubjects.isEmpty()) {
                    "No teachers available for class $className."
                } else {
                    "Invalid class name provided."
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }

    } else {
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
        ) {
            visibleSubjects.forEach { subject ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = subject,
                        modifier = Modifier.weight(1f)
                    )
                    TextField(
                        colors = OutlinedTextFieldDefaults.colors( unfocusedContainerColor = colorResource(id = R.color.inputUnfocused),
                            focusedContainerColor = colorResource(id = R.color.inputFocused) ),
                        value = if (Objects.isNull(subPeriodsPerWeek[subject])) {
                            updateSubPeriods(subject, 0)
                            "0"
                        } else {
                            subPeriodsPerWeek[subject].toString()
                        },
                        onValueChange = {
                            val p = it.toIntOrNull() ?: 0
                            updateSubPeriods(subject, p)
                        },

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    )
                }
                HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
            }
        }
    }
}