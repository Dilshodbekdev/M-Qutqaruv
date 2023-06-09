package com.technocorp.mqutqaruv.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.maps.android.compose.GoogleMap
import com.technocorp.mqutqaruv.presentation.screens.maps.MapsScreen
import com.technocorp.mqutqaruv.presentation.screens.navigation.Navigation
import com.technocorp.mqutqaruv.presentation.screens.navigation.Screen
import com.technocorp.mqutqaruv.presentation.screens.register.SignInScreen
import com.technocorp.mqutqaruv.ui.theme.MulteeTheme
import com.technocorp.mqutqaruv.util.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            0
        )

        val register = sharedPref.accessToken ?: ""

        setContent {
            val navController = rememberNavController()
            MulteeTheme {
                NavHost(
                    navController = navController,
                    startDestination = if (register.isNotEmpty() == true) Screen.Maps.route else Screen.Register.route
                ) {
                    composable(route = Screen.Register.route) {

                        SignInScreen {
                            navController.navigate(Screen.Maps.route) {
                                /*popUpTo(route = Screen.Register.route) {
                                    inclusive = true
                                }*/
                            }
                        }
                    }
                    composable(route = Screen.Maps.route) {
                        MapsScreen(navController = navController)
                    }
                }
            }
        }
    }
}