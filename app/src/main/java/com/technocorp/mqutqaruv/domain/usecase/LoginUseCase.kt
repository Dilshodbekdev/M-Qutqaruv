package com.technocorp.mqutqaruv.domain.usecase

import com.technocorp.mqutqaruv.data.remote.dto.LoginBody
import com.technocorp.mqutqaruv.data.remote.dto.toLogin
import com.technocorp.mqutqaruv.data.repository.MainRepositoryImpl
import com.technocorp.mqutqaruv.domain.model.Login
import com.technocorp.mqutqaruv.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: MainRepositoryImpl
) {

    operator fun invoke(body: LoginBody): Flow<Resource<Login>> = flow {
        try {
            emit(Resource.Loading<Login>())
            val data = repository.login(body).toLogin()
            emit(Resource.Success<Login>(data))
        } catch (e: HttpException) {
            emit(Resource.Error<Login>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Login>("Couldn't reach server. Check your internet connection."))
        }
    }
}