package com.technocorp.mqutqaruv.domain.model

import com.google.gson.annotations.SerializedName

data class Login(
    val refresh: String,
    val access: String
)
