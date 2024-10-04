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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ntech.ttmaker.R
//import com.example.ttmaker.R

import com.example.ttmaker.classes.Institute
import com.example.ttmaker.classes.types.TeacherInfo
import java.util.UUID

@Composable
fun Format() {
    Column {

        Text(
            text = "Upload text input",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier
//                    .fillMaxWidth(0.6f)
//                    .width(200.dp)
            ) {
                Text(
                    text = """
                InstituteName
                
                Days in week, hours per day
                
                Subjects name separated by ,
                
                First teacher_name
                His/her subjects name separated by ,
                Subjects respective class range
                
                Second teacher2_name
                His/her subjects name separated by ,
                subjects respective class range
            """.trimIndent(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier
                        .background(colorResource(id = R.color.bgLight))
                        .padding(8.dp)
//                        .fillMaxWidth()
                )
            }
//            InstituteName
//
//            days,hours
//
//            Math,Science,English
//
//            teacher_name
//            sub1,sub2,sub3.....
//            1-2,4-7,8-9..
//
//            teacher2_name
//            subx,suby,subz.....
//            3-3,4-8,3-3....
            Column(
//                modifier = Modifier.fillMaxWidth(0.4f)

            ) {

                Text(
                    text = """
                Sunrise Elementary

                5,7
                
                Math,Science,Art
                
                Anderson
                Math,Science
                1-2,3-5
                
                Miller
                Art
                6-7
            """.trimIndent(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier
                        .background(colorResource(id = R.color.bgLight))
                        .padding(8.dp)
//                        .fillMaxWidth()
                )
            }
        }

    }
}

@Composable
fun TextFileInput(
    onAddInstitute: (Institute) -> Unit,
    toLandingPage: () -> Unit
) {
    val context = LocalContext.current
    var csvContent by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var subjects by rememberSaveable { mutableStateOf("") }
    var days by rememberSaveable { mutableStateOf("") }
    var hours by rememberSaveable { mutableStateOf("") }
    var populationSize by rememberSaveable { mutableStateOf(100) }
    var generations by rememberSaveable { mutableStateOf(100) }
    var teachers by remember { mutableStateOf<List<TeacherInfo>>(emptyList()) }


    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Format()

        OutlinedTextField(
            value = csvContent,
            onValueChange = { csvContent = it },
            label = { Text("Give your institute information in above format") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(vertical = 8.dp)
        )

        Button(

            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.buttonLightHeavy) // Set the button's background color
            ),

            onClick = {
                val inst = textToInstitute(csvContent)
                if (inst != null) {
                    onAddInstitute(inst)
                    toLandingPage()
                }else{
                    Toast.makeText(context, "Wrong Input..", Toast.LENGTH_LONG)
                        .show()
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Parse and Create Institute")
        }
    }
}

fun textToInstitute(
    csvContent: String
): Institute? {

    var days = ""
    var hours = ""
    var name = ""
    var subjects = ""
    var teachers: MutableList<TeacherInfo> = mutableListOf()

    var populationSize = 100
    var generations = 100

    // Parse the CSV content
    val lines = csvContent
        .trim()
        .split("\n")
        .map { it.trim() }
        .filter { it.isNotEmpty() }

    if (lines.size < 3) {
        return null
    }
    // Extract institute details from the first line
    name = lines[0].trim()

    val daysHours = lines[1].split(",")
    if (daysHours.size == 2) {
        days = daysHours[0].trim()
        hours = daysHours[1].trim()
    } else return null

    subjects = lines[2].trim()
    var i = 3
    while (i < lines.size && i + 2 < lines.size) {
        // Extract teacher details
        val teacherName = lines[i].trim()
        val subjectsLine = lines[i + 1].trim()
        val classLevelsLine = lines[i + 2].trim()
        i += 3
        val subjectList = subjectsLine.split(",").map { it.trim() }
        val classLevelList = classLevelsLine.split(",").map {
            val rangeParts = it.split("-")
            if (rangeParts.size == 2) {
                val start = rangeParts[0].toIntOrNull()
                val end = rangeParts[1].toIntOrNull()
                if (start != null && end != null) {
                    Pair(start, end)
                } else {
                    null
                }
            } else {
                null
            }
        }.filterNotNull()
        if (subjectList.size == classLevelList.size) {
            teachers +=
                TeacherInfo(
                    name = teacherName,
                    subjects = subjectList,
                    classLevels = classLevelList
                )

        } else return null
    }
    val currentTimestamp = System.currentTimeMillis()
    val newInstitute = Institute(
        name = name,
        subjects = subjects.split(",").map { it.trim() },
        DAYS = days.toIntOrNull() ?: 0,
        HOURS = hours.toIntOrNull() ?: 0,
        GENERATIONS = generations,
        POPULATION_SIZE = populationSize,
        teachers = teachers,
        id = UUID.randomUUID().toString(),
        createdAt = currentTimestamp
    )
    return newInstitute
}

@Composable
fun Form(
    onAddInstitute: (Institute) -> Unit,
    toLandingPage: () -> Unit
) {
    // State variables for user input
    var name by rememberSaveable { mutableStateOf("") }
    var subjects by rememberSaveable { mutableStateOf("") }
    var days by rememberSaveable { mutableStateOf("") }
    var hours by rememberSaveable { mutableStateOf("") }
    var populationSize by rememberSaveable { mutableStateOf(100) }
    var generations by rememberSaveable { mutableStateOf(100) }
    var teacherName by rememberSaveable { mutableStateOf("") }
    var sub by rememberSaveable { mutableStateOf("") }
    var classes by rememberSaveable { mutableStateOf("") }
    var teachers by rememberSaveable { mutableStateOf<List<TeacherInfo>>(emptyList()) }

    val scrollState = rememberScrollState()

    //vh
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Column(
        modifier = Modifier
//                .height(screenHeight * 0.75f)
            .fillMaxSize()
//            .verticalScroll(scrollState)  // Apply vertical scrolling
//                .padding(bottom = 3.dp)
            .padding(15.dp)
    ) {
        Text(
            text = "Institute Details",

//                color = colorResource(id = R.color.textLightGrayPale)
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
                value = name,
                onValueChange = { name = it },
                label = { Text("Name",) },
                modifier = Modifier
                    .weight(1f) // Make each field take equal space
//                        .padding(vertical = 4.dp) // Add vertical padding for spacing
            )

            // Days Field
            OutlinedTextField(
                value = days,
                onValueChange = { days = it },
                label = { Text("Days",) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f) // Make each field take equal space
//                        .padding(vertical = 4.dp) // Add vertical padding for spacing
            )

            // Hours Field
            OutlinedTextField(
                value = hours,
                onValueChange = { hours = it },
                label = { Text("Hours",) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f) // Make each field take equal space
//                        .padding(vertical = 4.dp) // Add vertical padding for spacing
            )
        }


        // Step 2: Subjects
        Text(
            text = "Subjects",

//                color = colorResource(id = R.color.textLightGrayPale)
            modifier = Modifier.padding(top = 8.dp)

        )

        OutlinedTextField(
            value = subjects,
            onValueChange = { subjects = it },
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
            value = teacherName,
            onValueChange = { teacherName = it },
            label = { Text("Teacher's Name",) },
//                modifier = Modifier.padding(vertical = 4.dp)
        )

        // Subject and Classes

        OutlinedTextField(
            value = sub,
            onValueChange = { sub = it },
            label = { Text("e.g: Bio,Chem,Maths",) },
            modifier = Modifier.fillMaxWidth()
        )


        OutlinedTextField(
            value = classes,
            onValueChange = { classes = it },
            label = { Text("e.g: 5-5,6-9,9-12",) },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            OutlinedButton(
                onClick = {
                    if ((teacherName.isNotEmpty() && sub.isNotEmpty() && classes.isNotEmpty())) {
                        val subjectList =
                            sub.split(",").map { it.trim() } // Split subjects into list

                        // Convert classLevel string to list of pairs
                        val classLevelList = classes.split(",")
                            .mapNotNull { classRange ->
                                val rangeParts = classRange.trim().split("-")
                                if (rangeParts.size == 2) {
                                    val start = rangeParts[0].toIntOrNull()
                                    val end = rangeParts[1].toIntOrNull()
                                    if (start != null && end != null) {
                                        Pair(start, end)
                                    } else {
                                        null
                                    }
                                } else {
                                    null
                                }
                            }

                        // Create a new TeacherInfo object
                        val newTeacher = TeacherInfo(
                            name = teacherName,
                            subjects = subjectList,
                            classLevels = classLevelList
                        )
                        teachers += newTeacher

                        teacherName = ""
                        sub = ""
                        classes = ""
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
        TeacherList(teachers)
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
                    val subjectList = subjects.split(",").map { it.trim() }
                    val currentTimestamp = System.currentTimeMillis()
                    val newInstitute = Institute(
                        name = name,
                        subjects = subjectList,
                        DAYS = days.toIntOrNull() ?: 0,
                        HOURS = hours.toIntOrNull() ?: 0,
                        GENERATIONS = generations,
                        POPULATION_SIZE = populationSize,
                        teachers = teachers,
                        id = UUID.randomUUID().toString(),
                        createdAt = currentTimestamp
                    )
                    onAddInstitute(newInstitute)
                    toLandingPage()
                },
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text("Create Institute")
            }
        }
    }
}

@Composable
fun TeacherList(teachers: List<TeacherInfo>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        teachers.forEach {
            TeacherCard(teacher = it)
        }
    }
}

@Composable
fun TeacherCard(teacher: TeacherInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp) // Adding elevation for better visual appeal
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Teacher: ${teacher.name}",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Subjects         Classes",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Divider(color = Color.Gray, thickness = 1.dp)
            teacher.subjects.zip(teacher.classLevels).forEach { (subject, classLevel) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = subject,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "${classLevel.first}-${classLevel.second}",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.End
                    )
                }
                Divider(color = Color.Gray, thickness = 0.5.dp)
            }
        }
    }
}