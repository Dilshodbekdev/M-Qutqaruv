package com.technocorp.mqutqaruv.presentation.screens.maps

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.technocorp.mqutqaruv.util.LocationService

@Composable
fun MapsScreen(navController: NavHostController, mapViewModel: MapViewModel = hiltViewModel()) {

    val state = mapViewModel.state
    val context = LocalContext.current

    var currentLocation by remember {
        mutableStateOf(LatLng(41.19830328616144, 69.22181805224837))
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 20f)
    }

    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.SATELLITE))
    }

    var mapProperties by remember {
        mutableStateOf(
            MapProperties(maxZoomPreference = 10f, minZoomPreference = 5f)
        )
    }
    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(mapToolbarEnabled = false)
        )
    }

    LaunchedEffect(key1 = state.currentLocation) {
        /*state.currentLocation?.let {
            currentLocation = LatLng(it.latitude, it.longitude)
            cameraPositionState.position = CameraPosition.fromLatLngZoom(currentLocation, 20f)
        }*/
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
        ) {
            Marker(
                state = MarkerState(position = currentLocation),
                title = "Singapore",
                snippet = "Marker in Singapore"
            )
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .height(50.dp),
            onClick = {
                Intent(context, LocationService::class.java).apply {
                    action = LocationService.ACTION_START
                    context.startService(this)
                }
            }
        ) {
            Text(text = "Start")
        }
        /*Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .align(Alignment.BottomCenter)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .weight(1f),
                onClick = { }
            ) {
                Text(text = "Start")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .weight(1f),
                onClick = { }
            ) {
                Text(text = "Stop")
            }
        }*/
    }
}
