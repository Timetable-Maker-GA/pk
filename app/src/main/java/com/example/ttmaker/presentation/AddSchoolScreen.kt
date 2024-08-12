@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ttmaker.presentation


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.compose.backgroundLight
import com.example.ttmaker.ManropeFontFamily
import com.example.ttmaker.domain.enums.SchoolClass
import com.example.ttmaker.domain.models.School
import com.example.ttmaker.domain.models.Subject
import com.example.ttmaker.domain.models.Teacher
import com.example.ttmaker.presentation.components.TabControls

@Composable
fun AddSchoolScreen(
    navController: NavHostController
) {
    Scaffold(
        containerColor = Color(0xFFF2F3F4)
    ) { innerPadding ->
        TextTabs(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun TextTabs(
    navController: NavHostController, modifier: Modifier
) {
    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("Basic Info", "Add Schedule ", "Add Teachers")
    Column {
        PrimaryTabRow(
            selectedTabIndex = state, contentColor = Color.Blue, containerColor = Color.Transparent
        ) {
            titles.forEachIndexed { index, title ->
                Tab(selected = state == index, onClick = { state = index }, text = {
                    Text(
                        text = title,
                        maxLines = 2,
                        fontFamily = ManropeFontFamily,
                        fontWeight = FontWeight.Medium,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black
                    )
                })
            }
        }
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentColor = Color.Transparent,
            color = Color.Transparent
        ) {
            when (state) {
                0 -> BasicInfoTab()
                1 -> Text(text = "Tab 2")
                2 -> Text(text = "Tab 3 with lots of text")
            }
        }

        TabControls(state, setState = { v ->
            Log.d("TabControls", "s${v}")
            state = (v)
        })


    }
}

val sampleSchool = School(
    id = 1,
    name = "St. Mary's Convernt Sr. Sec. School",
    address = "123 Main St, City",
    teachers = listOf(
        Teacher(101, "John Doe", listOf(201)), Teacher(102, "Jane Smith", listOf(123))
    ),
    session = "2023-2024",
    classes = listOf(
        SchoolClass.NURSERY, SchoolClass.GRADE_1, SchoolClass.GRADE_3
    ),
    subjects = listOf(
        Subject(201, "Mathematics"), Subject(202, "Science"), Subject(203, "English")
    )
)


@Composable
fun BasicInfoTab() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {

        TextField(value = sampleSchool.name,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            label = {
                Text(text = "School Name")
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
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = sampleSchool.name,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            label = {
                Text(text = "School Name")
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
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            label = {
                Text(text = "School Name")
            },
            colors = TextFieldDefaults.colors(HB24jNIUh487HU
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                errorContainerColor = Color.Red,
                errorLabelColor = Color.Red,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
    }
}