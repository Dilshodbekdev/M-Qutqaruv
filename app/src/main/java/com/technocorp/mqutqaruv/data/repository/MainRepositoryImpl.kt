package com.technocorp.mqutqaruv.data.repository

import com.technocorp.mqutqaruv.data.remote.api.Api
import com.technocorp.mqutqaruv.data.remote.dto.LoginBody
import com.technocorp.mqutqaruv.data.remote.dto.LoginResponse
import com.technocorp.mqutqaruv.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: Api
) : MainRepository {
    override suspend fun login(body: LoginBody): LoginResponse {
        return api.login(body)
    }
}