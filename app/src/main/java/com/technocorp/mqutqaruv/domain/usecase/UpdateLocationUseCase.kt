package com.technocorp.mqutqaruv.domain.usecase

import com.technocorp.mqutqaruv.data.remote.dto.location_create.toCreateLocation
import com.technocorp.mqutqaruv.data.repository.MainRepositoryImpl
import com.technocorp.mqutqaruv.domain.model.CreateLocation
import com.technocorp.mqutqaruv.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateLocationUseCase @Inject constructor(
    private val repository: MainRepositoryImpl
) {

    operator fun invoke(id: Int): Flow<Resource<CreateLocation>> = flow {
        try {
            emit(Resource.Loading<CreateLocation>())
            val data = repository.updateLocation(id).toCreateLocation()
            emit(Resource.Success<CreateLocation>(data))
        } catch (e: HttpException) {
            emit(
                Resource.Error<CreateLocation>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<CreateLocation>("Couldn't reach server. Check your internet connection."))
        }
    }
}