package com.technocorp.mqutqaruv.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.technocorp.mqutqaruv.domain.model.Login

@Keep
data class LoginResponse(
    @SerializedName("refresh") val refresh: String?,
    @SerializedName("access") val access: String?
)

fun LoginResponse.toLogin(): Login {
    return Login(
        refresh = refresh ?: "",
        access = access ?: ""
    )
}
