//package com.example.ttmaker.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.runtime.remember
//import androidx.compose.ui.platform.LocalContext
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
////import com.example.ttmaker.data.LocalSchoolContext
////import com.example.ttmaker.data.SchoolContext
//import com.example.ttmaker.data.SchoolDatabase
//import com.example.ttmaker.data.SchoolRepository
//import com.example.ttmaker.presentation.AddSchoolScreen
//import com.example.ttmaker.presentation.home.HomeScreen
//import com.example.ttmaker.presentation.onBoarding.OnboardingScreen
//import com.example.ttmaker.presentation.subjects.SubjectSelectorScreen
//
//@Composable
//fun NavGraph() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "home_screen") {
//        composable(route = "onboarding") {
//            OnboardingScreen(navController = navController)
//        }
//        composable(route = "home_screen") {
//            HomeScreen(navController)
//        }
////        composable(route = "add_school_screen") {
////            AddSchoolScreen(navController)
////        }
////        composable(route = "add_tt_screen") {
////            AddTimeTableScreen(navController)
////        }
////        composable(route = "select_subject") {
////            SubjectSelectorScreen(navController)
////        }
////        composable(route = "onboarding") {
////            OnboardingScreen(navController = navController)
////        }
////        composable(route = "home_screen") {
////            HomeScreen(navController)
////        }
////        composable(route = "add_school_screen") {
////            AddSchoolScreen(navController)
////        }
////        composable(route = "add_tt_screen") {
////            AddTimeTableScreen(navController)
////        }
////        composable(route = "select_subject") {
////            SubjectSelectorScreen(navController)
////        }
////    }
//    }
//}
//
