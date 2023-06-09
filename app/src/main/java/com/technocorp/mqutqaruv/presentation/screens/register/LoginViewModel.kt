package com.technocorp.mqutqaruv.presentation.screens.register

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technocorp.mqutqaruv.data.remote.dto.LoginBody
import com.technocorp.mqutqaruv.data.remote.dto.location_create.CreateLocationBody
import com.technocorp.mqutqaruv.domain.model.CreateLocation
import com.technocorp.mqutqaruv.domain.model.Login
import com.technocorp.mqutqaruv.domain.usecase.CreateLocationUseCase
import com.technocorp.mqutqaruv.domain.usecase.LoginUseCase
import com.technocorp.mqutqaruv.util.LocationClient
import com.technocorp.mqutqaruv.util.Resource
import com.technocorp.mqutqaruv.util.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val createLocationUseCase: CreateLocationUseCase,
    private val locationClient: LocationClient,
    private val sharedPref: SharedPref
) : ViewModel() {

    var stateLogin by mutableStateOf(LoginUiState())
    var stateLocationCreate by mutableStateOf(CreateLocationUiState())

    fun saveToken(login: Login) {
        viewModelScope.launch {
            sharedPref.accessToken = login.access
            sharedPref.refreshToken = login.refresh
        }
    }

    fun saveId(id: Int) {
        viewModelScope.launch {
            sharedPref.id = id
        }
    }

    fun saveAutoNumber(autoNumber: String) {
        viewModelScope.launch {
            sharedPref.autoNumber = autoNumber
        }
    }

    fun login(body: LoginBody) {
        loginUseCase(body).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    stateLogin = stateLogin.copy(
                        isLoading = false,
                        login = resource.data,
                        error = null
                    )
                }

                is Resource.Loading -> {
                    stateLogin = stateLogin.copy(
                        isLoading = true,
                        login = null,
                        error = null
                    )
                }

                is Resource.Error -> {
                    stateLogin = stateLogin.copy(
                        isLoading = false,
                        login = null,
                        error = resource.message ?: "An unexpected error occured"
                    )
                }
            }

        }.launchIn(viewModelScope)
    }

    fun createLocation(avtoNumber: String) {
        viewModelScope.launch {
            stateLocationCreate = stateLocationCreate.copy(
                isLoading = true,
                createLocation = null,
                error = null
            )
            locationClient.getCurrentLocation()?.let { location ->
                when (val resource = createLocationUseCase(
                    CreateLocationBody(
                        avto_raqam = avtoNumber,
                        latitude = location.latitude,
                        longtitude = location.longitude
                    )
                )) {
                    is Resource.Success -> {
                        stateLocationCreate = stateLocationCreate.copy(
                            isLoading = false,
                            createLocation = resource.data,
                            error = null
                        )
                    }

                    is Resource.Loading -> {
                        stateLocationCreate = stateLocationCreate.copy(
                            isLoading = true,
                            createLocation = null,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        stateLocationCreate = stateLocationCreate.copy(
                            isLoading = false,
                            createLocation = null,
                            error = resource.message ?: "An unexpected error occured"
                        )
                    }
                }
            } ?: run {
                stateLocationCreate = stateLocationCreate.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }
}

data class CreateLocationUiState(
    val isLoading: Boolean = false,
    val createLocation: CreateLocation? = null,
    val error: String? = null
)

data class LoginUiState(
    val isLoading: Boolean = false,
    val login: Login? = null,
    val error: String? = null
)