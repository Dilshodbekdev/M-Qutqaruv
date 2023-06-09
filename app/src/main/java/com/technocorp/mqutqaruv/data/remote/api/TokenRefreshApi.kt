package com.technocorp.mqutqaruv.data.remote.api

import com.technocorp.mqutqaruv.data.remote.dto.LoginResponse
import com.technocorp.mqutqaruv.data.remote.dto.token.TokenRefreshBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenRefreshApi {

    @POST("api/token/refresh/")
    suspend fun tokenRefresh(@Body body: TokenRefreshBody): Response<LoginResponse>
}