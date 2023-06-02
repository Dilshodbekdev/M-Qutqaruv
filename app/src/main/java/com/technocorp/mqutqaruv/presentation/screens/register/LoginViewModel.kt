package com.technocorp.mqutqaruv.presentation.screens.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technocorp.mqutqaruv.data.remote.dto.LoginBody
import com.technocorp.mqutqaruv.domain.model.Login
import com.technocorp.mqutqaruv.domain.usecase.LoginUseCase
import com.technocorp.mqutqaruv.util.Resource
import com.technocorp.mqutqaruv.util.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase,
    private val sharedPref: SharedPref
) : ViewModel() {

    var state by mutableStateOf(LoginUiState())

    fun saveToken(login: Login) {
        viewModelScope.launch {
            sharedPref.accessToken = login.access
            sharedPref.refreshToken = login.refresh
        }
    }

    fun login(body: LoginBody) {
        useCase(body).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    state = state.copy(
                        isLoading = false,
                        login = resource.data,
                        error = null
                    )
                }

                is Resource.Loading -> {
                    state = state.copy(
                        isLoading = true,
                        login = null,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        login = null,
                        error = resource.message ?: "An unexpected error occured"
                    )
                }
            }

        }.launchIn(viewModelScope)
    }

}

data class LoginUiState(
    val isLoading: Boolean = false,
    val login: Login? = null,
    val error: String? = null
)