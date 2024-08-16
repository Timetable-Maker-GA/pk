package com.example.ttmaker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ttmaker.presentation.AddSchoolScreen
import com.example.ttmaker.presentation.AddTimeTableScreen
import com.example.ttmaker.presentation.HomeScreen
import com.example.ttmaker.presentation.OnboardingScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "add_school_screen") {
        composable(route = "onboarding") {
            OnboardingScreen(navController)
        }
        composable(route = "home_screen") {
            HomeScreen(navController)
        }
        composable(route = "add_school_screen"){
            AddSchoolScreen(navController)
        }
        composable(route = "add_tt_screen"){
            AddTimeTableScreen(navController)
        }
    }
}

