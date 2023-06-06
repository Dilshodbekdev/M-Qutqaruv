package com.technocorp.mqutqaruv.data.remote.dto.token

import com.google.gson.annotations.SerializedName

data class TokenRefreshBody(
    @SerializedName("refresh")
    val refresh: String
)