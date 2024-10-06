package com.example.ttmaker.presentation.addSchool.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ttmaker.TTMakerApplication
import com.example.ttmaker.model.ClassLevel
import com.ntech.ttmaker.R
import com.example.ttmaker.model.TeacherInfo

@Composable
fun Form(
) {

    val scrollState = rememberScrollState()

    //vh
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    // Access the application context
    val context = LocalContext.current
    val app = context.applicationContext as TTMakerApplication

    val vm: FormViewModel = viewModel(factory = FormViewModelFactory(app.schoolRepository))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Text(
            text = "Institute Details",
            modifier = Modifier.padding(top = 4.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth() // Make sure the Row takes full width
                .padding(horizontal = 0.dp), // Add horizontal padding for spacing
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between TextFields
        ) {
            // Institute Name Field
            OutlinedTextField(
                value = vm.name.value,
                onValueChange = { vm.updateName(it) },
                label = { Text("Name",) },
                modifier = Modifier
                    .weight(1f)
            )

            // Days Field
            OutlinedTextField(
                value = vm.days.value,
                onValueChange = { vm.updateDays(it) },
                label = { Text("Days",) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
            )

            // Hours Field
            OutlinedTextField(
                value = vm.hours.value,
                onValueChange = { vm.updateHours(it) },
                label = { Text("Hours",) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
            )
        }


        // Step 2: Subjects
        Text(
            text = "Subjects",

//                color = colorResource(id = R.color.textLightGrayPale)
            modifier = Modifier.padding(top = 8.dp)

        )

        OutlinedTextField(
            value = vm.subjects.value,
            onValueChange = { vm.updateSubjects(it) },
            label = { Text("Subjects (comma separated)",) },
//                modifier = Modifier.padding(vertical = 4.dp)
        )

        // Step 3: Teachers' Subject & Class Level
        Text(
            text = "Teachers' Subject & Class Level",

//                color = colorResource(id = R.color.textLightGrayPale),
            modifier = Modifier.padding(top = 8.dp)
        )

        // Teacher's Name
        OutlinedTextField(
            value = vm.teacherName.value,
            onValueChange = { vm.updateTeacherName(it) },
            label = { Text("Teacher's Name",) },
//                modifier = Modifier.padding(vertical = 4.dp)
        )

        // Subject and Classes

        OutlinedTextField(
            value = vm.teachersSub.value,
            onValueChange = { vm.updateTeachersSub(it) },
            label = { Text("e.g: Bio,Chem,Maths",) },
            modifier = Modifier.fillMaxWidth()
        )


        OutlinedTextField(
            value = vm.classes.value,
            onValueChange = { vm.updateClasses(it) },
            label = { Text("e.g: 5-5,6-9,9-12",) },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            OutlinedButton(
                onClick = {
                    if ((vm.teacherName.value.isNotEmpty() && vm.teachersSub.value.isNotEmpty() && vm.classes.value.isNotEmpty())) {
                        val subjectList =
                            vm.teachersSub.value.split(",").map { it.trim() } // Split subjects into list

                        // Convert classLevel string to list of pairs
                        val classLevelList = vm.classes.value.split(",")
                            .mapNotNull { classRange ->
                                val rangeParts = classRange.trim().split("-")
                                if (rangeParts.size == 2) {
                                    val start = rangeParts[0].toIntOrNull()
                                    val end = rangeParts[1].toIntOrNull()
                                    if (start != null && end != null) {
                                        ClassLevel(start, end)
                                    } else {
                                        null
                                    }
                                } else {
                                    null
                                }
                            }

                        // Create a new TeacherInfo object
                        val newTeacher = TeacherInfo(
                            name = vm.teacherName.value,
                            subjects = subjectList,
                            classLevels = classLevelList
                        )
                        vm.addTeacher(newTeacher)
//                        teachers += newTeacher

                        vm.updateTeacherName("")
                        vm.updateTeachersSub("")
                        vm.updateClasses("")
                    }
                },
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(
                    "Add Teacher Info",
                    color = colorResource(id = R.color.textLightBlueHeavy)
                )
            }
        }
        TeacherList(vm.teachers.value)
        // Create Institute Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.buttonLightHeavy) // Set the button's background color
                ),
                onClick = {
                          vm.insertSchool()
                },
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text("Create " + app.schoolOrInstitute)
            }
        }
    }
}