@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ttmaker.presentation


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.example.ttmaker.ManropeFontFamily
import com.example.ttmaker.presentation.addSchool.components.AddSchedule
import com.example.ttmaker.presentation.addSchool.components.BasicInfoForm
import com.example.ttmaker.presentation.shared.TabControls


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSchoolScreen(
    navController: NavHostController
) {
    Scaffold(
        containerColor = Color(0xFFF2F3F4),
    ) { innerPadding ->
        var state by remember { mutableIntStateOf(0) }
        val titles = listOf("Basic Info", "Add Schedule ")
        Column {
            PrimaryTabRow(
                selectedTabIndex = state,
                contentColor = Color.Blue,
                containerColor = Color.Transparent,
                modifier = Modifier.padding(innerPadding)
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
                    0 -> BasicInfoForm()
                    1 -> AddSchedule()
                }
            }
            TabControls(state, setState = { v ->
                Log.d("TabControls", "s${v}")
                state = (v)
            })
        }
    }
}




