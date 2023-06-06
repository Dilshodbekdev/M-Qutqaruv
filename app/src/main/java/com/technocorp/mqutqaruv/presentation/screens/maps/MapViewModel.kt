package com.technocorp.mqutqaruv.presentation.screens.maps

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technocorp.mqutqaruv.util.LocationClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationClient: LocationClient
) : ViewModel() {

    var state by mutableStateOf(MapUiState())
        private set

    init {
        state = state.copy(
            isLoading = true,
            error = null,
            currentLocation = null
        )
        viewModelScope.launch {
            locationClient.getCurrentLocation()?.let { location ->
                state = state.copy(
                    isLoading = false,
                    error = null,
                    currentLocation = location
                )
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }
}

data class MapUiState(
    val currentLocation: Location? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)