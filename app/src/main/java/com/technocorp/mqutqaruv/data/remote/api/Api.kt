package com.technocorp.mqutqaruv.data.remote.api

import com.technocorp.mqutqaruv.data.remote.dto.LoginBody
import com.technocorp.mqutqaruv.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("api/token/")
    suspend fun login(@Body body: LoginBody): LoginResponse

}