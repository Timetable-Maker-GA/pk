package com.example.ttmaker.presentation

import Header
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.ttmaker.presentation.components.CreatedTTList
import com.example.ttmaker.presentation.components.HomeControls
import com.ntech.ttmaker.R

@Composable
fun HomeScreen(
    navController: NavHostController,
) {
    Scaffold(
        modifier = Modifier
            .padding()
            .fillMaxWidth(), backgroundColor = Color(0xFFf2f3f4)
,
        bottomBar = {
            HomeControls(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Header(profileImage = R.drawable.profile)
            CreatedTTList()
        }


    }
}


