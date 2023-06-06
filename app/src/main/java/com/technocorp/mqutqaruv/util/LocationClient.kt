package com.technocorp.mqutqaruv.util

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClient {
    fun getLocationUpdates(interval: Long): Flow<Location>

    suspend fun getCurrentLocation(): Location?

    class LocationException(message: String) : Exception()
}