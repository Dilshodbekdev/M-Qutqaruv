package com.technocorp.mqutqaruv.domain.repository

import com.technocorp.mqutqaruv.data.remote.dto.LoginBody
import com.technocorp.mqutqaruv.data.remote.dto.LoginResponse
import com.technocorp.mqutqaruv.data.remote.dto.location_create.CreateLocationBody
import com.technocorp.mqutqaruv.data.remote.dto.location_create.CreateLocationResponse

interface MainRepository {

    suspend fun login(body: LoginBody): LoginResponse

    suspend fun createLocation(body: CreateLocationBody): CreateLocationResponse

    suspend fun updateLocation(id: Int): CreateLocationResponse
}