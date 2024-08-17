package com.example.ttmaker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ttmaker.presentation.addSchool.AddSchoolScreen
import com.example.ttmaker.presentation.addTimeTable.AddTimeTableScreen
import com.example.ttmaker.presentation.homeScreen.HomeScreen
import com.example.ttmaker.presentation.onBoarding.OnboardingScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home_screen") {
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

