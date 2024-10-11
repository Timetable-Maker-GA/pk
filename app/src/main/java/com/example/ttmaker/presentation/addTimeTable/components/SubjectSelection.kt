package com.example.ttmaker.presentation.addTimeTable.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.util.Objects

import com.example.ttmaker.data.SchoolEntity

@Composable
fun SubjectSelection(
    selectedInstitute: SchoolEntity, className: String, subPeriodsPerWeek: Map<String, Int>,
    updateSubPeriods: (subject: String, periods: Int) -> Unit
) {
    val visibleSubjects: MutableSet<String> = mutableSetOf()
    for (teacher in selectedInstitute.teachers) {
        teacher.subjects.zip(teacher.classLevels).forEach { (subject, classLevel) ->
            if ((className.toIntOrNull() ?: -1) in classLevel.start..classLevel.end) {
                visibleSubjects.add(subject)
            }
        }
    }
    if (Objects.isNull(className.toIntOrNull())) {
        Column {
            Text(
                text = "No Valid Classname given",
//                fontSize = FontSizes.subtitle,
            )
        }
    } else {
        Column() {

            visibleSubjects.forEach { subject ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = subject,
//                        fontSize = FontSizes.body,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
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
                        label = {
                            Text(
                                "Periods/Week",
//                                fontSize = FontSizes.body,
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp), // Adjust height
//                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp) // Adjust padding

                    )
                }
                Divider(color = Color.Gray, thickness = 0.5.dp)
            }

        }
    }
}