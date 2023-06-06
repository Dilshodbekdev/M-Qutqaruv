package com.technocorp.mqutqaruv.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.technocorp.mqutqaruv.presentation.screens.maps.MapsScreen
import com.technocorp.mqutqaruv.presentation.screens.register.LoginViewModel
import com.technocorp.mqutqaruv.presentation.screens.register.SignInScreen
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
