package com.technocorp.mqutqaruv.domain.usecase

import com.technocorp.mqutqaruv.data.remote.dto.LoginBody
import com.technocorp.mqutqaruv.data.remote.dto.location_create.CreateLocationBody
import com.technocorp.mqutqaruv.data.remote.dto.location_create.toCreateLocation
import com.technocorp.mqutqaruv.data.remote.dto.toLogin
import com.technocorp.mqutqaruv.data.repository.MainRepositoryImpl
import com.technocorp.mqutqaruv.domain.model.CreateLocation
import com.technocorp.mqutqaruv.domain.model.Login
import com.technocorp.mqutqaruv.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreateLocationUseCase @Inject constructor(
    private val repository: MainRepositoryImpl
) {

    suspend operator fun invoke(body: CreateLocationBody): Resource<CreateLocation> {
        return try {
            Resource.Loading<CreateLocation>()
            val data = repository.createLocation(body).toCreateLocation()
            Resource.Success<CreateLocation>(data)
        } catch (e: HttpException) {
            Resource.Error<CreateLocation>(
                e.localizedMessage ?: "An unexpected error occured"
            )

        } catch (e: IOException) {
            Resource.Error<CreateLocation>("Couldn't reach server. Check your internet connection.")
        }
    }
}