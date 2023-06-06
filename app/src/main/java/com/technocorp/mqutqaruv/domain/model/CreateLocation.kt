package com.technocorp.mqutqaruv.domain.model

import com.google.gson.annotations.SerializedName

data class CreateLocation(
    val id: Int,
    val avto_raqam: String,
    val latitude: Float,
    val longtitude: Float,
    val timestamp: String,
    val created: String
)