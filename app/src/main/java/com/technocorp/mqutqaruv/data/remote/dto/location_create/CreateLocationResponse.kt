package com.technocorp.mqutqaruv.data.remote.dto.location_create

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.technocorp.mqutqaruv.domain.model.CreateLocation

@Keep
data class CreateLocationResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("avto_raqam")
    val avto_raqam: String?,
    @SerializedName("latitude")
    val latitude: Float?,
    @SerializedName("longtitude")
    val longtitude: Float?,
    @SerializedName("timestamp")
    val timestamp: String?,
    @SerializedName("created")
    val created: String?
)

fun CreateLocationResponse.toCreateLocation(): CreateLocation {
    return CreateLocation(
        id = id ?: 0,
        avto_raqam = avto_raqam ?: "",
        latitude = latitude ?: 0.0f,
        longtitude = longtitude ?: 0.0f,
        timestamp = timestamp ?: "",
        created = created ?: ""
    )
}
