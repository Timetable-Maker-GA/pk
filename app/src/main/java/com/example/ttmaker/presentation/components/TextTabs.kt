package com.example.ttmaker.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
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
import com.example.ttmaker.data.sampleSchool
import com.example.ttmaker.presentation.AddSchedule
import com.example.ttmaker.presentation.BasicInfoForm

@OptIn(ExperimentalMaterial3Api::class)
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
                0 -> BasicInfoForm()
                1 -> AddSchedule()
                2 -> Text(text = "Tab 3 with lots of text")
            }
        }

        TabControls(state, setState = { v ->
            Log.d("TabControls", "s${v}")
            state = (v)
        })


    }
}