package com.technocorp.mqutqaruv.screens.maps

import android.Manifest
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.maps.android.compose.GoogleMap
import com.technocorp.mqutqaruv.screens.permission.RequestPermission

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapsScreen(navController: NavHostController) {
    RequestPermission(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    /*GoogleMap(
        modifier = Modifier.fillMaxSize( )
    )*/
}