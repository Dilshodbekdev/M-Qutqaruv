package com.technocorp.mqutqaruv.data.repository

import com.technocorp.mqutqaruv.data.remote.api.Api
import com.technocorp.mqutqaruv.data.remote.dto.LoginBody
import com.technocorp.mqutqaruv.data.remote.dto.LoginResponse
import com.technocorp.mqutqaruv.data.remote.dto.location_create.CreateLocationBody
import com.technocorp.mqutqaruv.data.remote.dto.location_create.CreateLocationResponse
import com.technocorp.mqutqaruv.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: Api
) : MainRepository {
    override suspend fun login(body: LoginBody): LoginResponse {
        return api.login(body)
    }

    override suspend fun createLocation(body: CreateLocationBody): CreateLocationResponse {
        return api.createLocation(body)
    }

    override suspend fun updateLocation(id: Int, body: CreateLocationBody): CreateLocationResponse {
        return api.updateLocation(id, body)
    }
}