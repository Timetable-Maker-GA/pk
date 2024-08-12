package com.example.ttmaker.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun OnboardingScreen(
    navController: NavHostController
){
Column {
    Text(text = "Onboarding Screen")
    Button(onClick = {
        navController.navigate("home_screen")
    }) {
        Text(text = "Onboarding Screen")
    }
}
}