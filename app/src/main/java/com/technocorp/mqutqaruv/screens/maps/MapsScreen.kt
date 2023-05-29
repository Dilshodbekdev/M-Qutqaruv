package com.technocorp.mqutqaruv.screens.maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.maps.android.compose.GoogleMap

@Composable
fun MapsScreen(navController: NavHostController) {
    GoogleMap(
        modifier = Modifier.fillMaxSize( )
    )
}