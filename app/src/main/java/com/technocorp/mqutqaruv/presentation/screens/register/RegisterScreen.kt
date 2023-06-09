package com.technocorp.mqutqaruv.presentation.screens.register

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.technocorp.mqutqaruv.R
import com.technocorp.mqutqaruv.data.remote.dto.LoginBody
import com.technocorp.mqutqaruv.data.remote.dto.location_create.CreateLocationBody
import com.technocorp.mqutqaruv.presentation.screens.components.PhoneField
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(viewModel: LoginViewModel = hiltViewModel(), onLoginClick: () -> Unit) {

    val stateLogin = viewModel.stateLogin
    val stateCreateLocation = viewModel.stateLocationCreate

    var username by remember { mutableStateOf("") }
    var usernameError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }
    var passwordHidden by remember { mutableStateOf(true) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    stateLogin.login.let {
        if (it?.access?.isNotEmpty() == true) {
            viewModel.saveToken(it)
            LaunchedEffect(key1 = it.access) {
                viewModel.createLocation(username)
            }
        }
    }

    stateCreateLocation.createLocation?.let {
        if (it.id != 0) {
            viewModel.saveId(it.id)
            viewModel.saveAutoNumber(username)
            onLoginClick()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
                .padding(padding)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .height(160.dp)
                    .width(160.dp)
                    .padding(top = 32.dp),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "logo"
            )
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.88f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sign In",
                    style = MaterialTheme.typography.h4,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    value = username,
                    placeholder = { Text(text = "Auto number") },
                    onValueChange = {
                        username = it
                        if (it.isNotEmpty()) usernameError = false
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    shape = RoundedCornerShape(15.dp),
                    isError = usernameError,
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    value = password,
                    placeholder = { Text(text = "Password") },
                    onValueChange = {
                        password = it
                        if (it.isNotEmpty()) passwordError = false
                    },
                    visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        IconButton(onClick = { passwordHidden = !passwordHidden }) {
                            val visibilityIcon =
                                if (passwordHidden) painterResource(id = R.drawable.baseline_visibility_24)
                                else painterResource(id = R.drawable.baseline_visibility_off_24)
                            // Please provide localized description for accessibility services
                            val description =
                                if (passwordHidden) "Show password" else "Hide password"
                            Icon(painter = visibilityIcon, contentDescription = description)
                        }
                    },
                    shape = RoundedCornerShape(15.dp),
                    isError = passwordError
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        if (username.isNotEmpty() && password.isNotEmpty()) {
                            viewModel.login(LoginBody(username, password))
                        } else {
                            if (username.isEmpty())
                                usernameError = true
                            if (password.isEmpty())
                                passwordError = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.main_red))
                ) {
                    Text(
                        text = "Sign In",
                        style = MaterialTheme.typography.body2,
                        color = Color.White
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (stateLogin.isLoading) {
                    CircularProgressIndicator()
                } else if (stateLogin.error != null) {
                    Text(
                        text = stateLogin.error,
                        color = MaterialTheme.colors.error
                    )
                }
            }
        }
    }
}
