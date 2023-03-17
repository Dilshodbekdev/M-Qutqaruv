package com.technocorp.mqutqaruv.screens.navigation

import android.transition.Scene
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.technocorp.mqutqaruv.screens.maps.MapsScreen
import com.technocorp.mqutqaruv.screens.register.SignInScreen
import com.technocorp.mqutqaruv.util.SharedPref

@Composable
fun Navigation(navController: NavHostController) {

    val context = LocalContext.current
    val register = SharedPref.getInstance(context).register

    NavHost(
        navController = navController,
        startDestination = if (register == true) Screen.Maps.route else Screen.Register.route
    ) {
        composable(route = Screen.Register.route) {
            SignInScreen(navController = navController)
        }
        composable(route = Screen.Maps.route) {
            MapsScreen(navController = navController)
        }
    }
}
