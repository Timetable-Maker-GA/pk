package com.ntech.ttmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier


import com.example.ttmaker.pages.MainPageContent


import androidx.compose.material3.ExperimentalMaterial3Api
import com.google.android.gms.ads.MobileAds


class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        MobileAds.initialize(this) {}
        setContent {

            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
            ) {
                MainPageContent()
            }
        }
    }
}