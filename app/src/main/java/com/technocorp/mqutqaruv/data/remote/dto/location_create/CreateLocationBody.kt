package com.technocorp.mqutqaruv.data.remote.dto.location_create

import com.google.gson.annotations.SerializedName

data class CreateLocationBody(
    @SerializedName("avto_raqam")
    val avto_raqam: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longtitude")
    val longtitude: Double
)