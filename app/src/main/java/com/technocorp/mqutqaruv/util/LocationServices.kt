package com.technocorp.mqutqaruv.util

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.LocationServices
import com.technocorp.mqutqaruv.R
import com.technocorp.mqutqaruv.data.remote.api.Api
import com.technocorp.mqutqaruv.data.remote.dto.location_create.CreateLocationBody
import com.technocorp.mqutqaruv.domain.usecase.UpdateLocationUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class LocationService : Service() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Inject
    lateinit var locationClient: LocationClient

    @Inject
    lateinit var useCase: UpdateLocationUseCase

    @Inject
    lateinit var pref: SharedPref

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    @SuppressLint("NotificationPermission")
    private fun start() {
        val notification = NotificationCompat.Builder(this, "location")
            .setContentTitle("Tracking location...")
            .setContentText("Location: null")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationClient
            .getLocationUpdates(10000L)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                useCase(
                    id = pref.id ?: 0, body = CreateLocationBody(
                        avto_raqam = pref.autoNumber ?: "",
                        longtitude = location.longitude,
                        latitude = location.latitude
                    )
                ).launchIn(serviceScope)
                val lat = location.latitude.toString().takeLast(3)
                val long = location.longitude.toString().takeLast(3)
                val updatedNotification = notification.setContentText(
                    "Location: ($lat, $long)"
                )
                notificationManager.notify(1, updatedNotification.build())
            }
            .launchIn(serviceScope)

        startForeground(1, notification.build())
    }

    private fun stop() {
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }
}