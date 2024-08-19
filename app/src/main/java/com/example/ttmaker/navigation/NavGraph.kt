package com.example.ttmaker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ttmaker.presentation.AddSchoolScreen
import com.example.ttmaker.presentation.addTimeTable.AddTimeTableScreen
import com.example.ttmaker.presentation.home.HomeScreen
import com.example.ttmaker.presentation.onBoarding.OnboardingScreen
import com.example.ttmaker.presentation.subjects.SubjectSelectorScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home_screen") {
        composable(route = "onboarding") {
            OnboardingScreen(navController = navController)
        }
        composable(route = "home_screen") {
            HomeScreen(navController)
        }
        composable(route = "add_school_screen") {
            AddSchoolScreen(navController)
        }
        composable(route = "add_tt_screen") {
            AddTimeTableScreen(navController)
        }
        composable(route = "select_subject") {
            SubjectSelectorScreen(navController)
        }
    }
}

