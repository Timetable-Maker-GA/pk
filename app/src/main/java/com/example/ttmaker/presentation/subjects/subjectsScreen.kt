package com.example.ttmaker.presentation.subjects

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ttmaker.presentation.shared.CustomCheckbox
import com.example.ui.theme.manrope
import com.ntech.ttmaker.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectSelectorScreen(
    navController: NavHostController
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val allSubjects = remember {
        listOf(
            "Art and Craft",
            "Environmental Studies",
            "Mathematics",
            "English",
            "Hindi",
            "General Knowledge",
            "Science",
            "Physical Education",
            "Music",
            "Dance",
            "Social Studies",
            "Computer Science",
            "Social Science",
            "Sanskrit",
            "Science (Physics, Chemistry, Biology)",
            "Social Science (History, Geography, Political Science, Economics)",
            "Arts (Optional)",
            "Business Studies (Optional)",
            "Economics (Optional)",
            "Physics",
            "Chemistry",
            "Biology",
            "Information Technology",
            "Statistics",
            "Accountancy",
            "Business Studies",
            "History",
            "Geography",
            "Political Science",
            "Sociology",
            "Psychology",
            "Home Science",
            "Fashion Designing",
            "Hotel Management",
            "Tourism",
            "Agriculture",
            "Health and Physical Education"
        )
    }.sorted()
    var selectedSubjects by remember { mutableStateOf(setOf<String>()) }

    Scaffold(topBar = {
        LargeTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = Color.Transparent
            ),
            title = { Text("Select Subjects") },
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
    },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(16.dp),
                containerColor = Color.Transparent
            ) {
                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3165FF), contentColor = Color.White
                    ),
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .weight(1f),
                    shape = RoundedCornerShape(20.dp),

                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    Text(
                        text = "Add",
                        fontFamily = manrope,
                        style = MaterialTheme.typography.bodyMedium,

                        )

                }
            }
        },
        containerColor = Color(0xFFF2F3F4),
        modifier = Modifier.background(MaterialTheme.colorScheme.error)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            SearchBar(
                query = searchQuery,
                onQueryChanged = { searchQuery = it },

                )
            Spacer(modifier = Modifier.height(16.dp))
            SubjectList(subjects = allSubjects.filter {
                it.contains(searchQuery.text, ignoreCase = true)
            }, selectedSubjects = selectedSubjects, onSubjectSelected = { subject ->
                selectedSubjects = if (selectedSubjects.contains(subject)) {
                    selectedSubjects - subject
                } else {
                    selectedSubjects + subject
                }
            })
        }
    }
}

@Composable
fun SearchBar(
    query: TextFieldValue, onQueryChanged: (TextFieldValue) -> Unit
) {

    TextField(
        value = query,
        onValueChange = { text -> onQueryChanged(text) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        textStyle = TextStyle.Default.copy(
            fontSize = 20.sp, fontFamily = manrope, fontWeight = FontWeight.Normal
        ),
        placeholder = {
            Text(text = "Search for subjects...")
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            errorContainerColor = Color.Red,
            errorLabelColor = Color.Red,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        maxLines = 1,


        )


}

@Composable
fun SubjectList(
    subjects: List<String>, selectedSubjects: Set<String>, onSubjectSelected: (String) -> Unit
) {
    LazyColumn {
        items(subjects) { subject ->
            SubjectItem(
                subject = subject,
                isSelected = selectedSubjects.contains(subject),
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun SubjectItem(subject: String, isSelected: Boolean) {
    var isChecked by remember { mutableStateOf(isSelected) }
    var totalSubjects by remember {
        mutableStateOf(0)
    }
    if (totalSubjects>0) {
        isChecked=true
    }else{
        isChecked=false
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { isChecked = !isChecked }
        .border(width = 1.dp, color = Color(0xFFF0F5FE), shape = RoundedCornerShape(24.dp))
        .clip(RoundedCornerShape(24.dp))
        .shadow(elevation = 24.dp, ambientColor = Color.Red)
        .background(Color(0xFFFFFFFF))
        .padding(vertical = 8.dp , horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Row {
            CustomCheckbox(checked = isChecked, onCheckedChange = { isChecked = it })

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = subject,
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        Row(
            modifier = Modifier

                .padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = {
                totalSubjects--
            }) {
                Icon(painter = painterResource(id = R.drawable.minus), contentDescription = "")
            }
            Text(
                text = "${totalSubjects}",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = manrope,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            IconButton(onClick = {
                totalSubjects++
            }) {
                Icon(painter = painterResource(id = R.drawable.add), contentDescription = "")
            }
        }
    }
}

