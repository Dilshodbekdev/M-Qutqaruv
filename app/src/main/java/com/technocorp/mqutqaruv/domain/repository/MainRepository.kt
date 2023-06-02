package com.technocorp.mqutqaruv.domain.repository

import com.technocorp.mqutqaruv.data.remote.dto.LoginBody
import com.technocorp.mqutqaruv.data.remote.dto.LoginResponse

interface MainRepository {

    suspend fun login(body: LoginBody): LoginResponse
}