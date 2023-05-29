package com.technocorp.mqutqaruv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.maps.android.compose.GoogleMap
import com.technocorp.mqutqaruv.screens.navigation.Navigation
import com.technocorp.mqutqaruv.ui.theme.MulteeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            MulteeTheme {

                Navigation(navController = navController)
            }
        }
    }
}