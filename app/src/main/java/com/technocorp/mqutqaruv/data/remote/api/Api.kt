package com.technocorp.mqutqaruv.data.remote.api

import com.technocorp.mqutqaruv.data.remote.dto.LoginBody
import com.technocorp.mqutqaruv.data.remote.dto.LoginResponse
import com.technocorp.mqutqaruv.data.remote.dto.location_create.CreateLocationBody
import com.technocorp.mqutqaruv.data.remote.dto.location_create.CreateLocationResponse
import com.technocorp.mqutqaruv.data.remote.dto.token.TokenRefreshBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface Api {

    @POST("api/token/")
    suspend fun login(@Body body: LoginBody): LoginResponse

    @POST("api/token/refresh/")
    suspend fun tokenRefresh(@Body body: TokenRefreshBody): LoginResponse

    @POST("api/avto-location/")
    suspend fun createLocation(@Body body: CreateLocationBody): CreateLocationResponse

    @PUT("api/avto-location{id}")
    suspend fun updateLocation(@Part id: Int): CreateLocationResponse
}