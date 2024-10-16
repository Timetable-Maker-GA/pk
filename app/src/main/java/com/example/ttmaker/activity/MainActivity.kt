package com.example.ttmaker.activity


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
//import com.example.ttmaker.navigation.NavGraph
import com.example.ttmaker.presentation.home.HomeScreen
import com.example.ttmaker.presentation.home.components.HomeControls
import com.google.android.gms.ads.MobileAds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this) {}
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
//                NavGraph()
                HomeScreen()
            }
        }
    }
}