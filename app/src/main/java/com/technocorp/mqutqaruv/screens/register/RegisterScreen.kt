package com.technocorp.mqutqaruv.screens.register

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.technocorp.mqutqaruv.R
import com.technocorp.mqutqaruv.screens.navigation.Screen

@Composable
fun SignInScreen(navController: NavHostController) {

    val context = LocalContext.current
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
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
                text = "Kirish",
                style = MaterialTheme.typography.h4,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                value = login,
                placeholder = { Text(text = "Login") },
                onValueChange = { login = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(15.dp),
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                value = password,
                placeholder = { Text(text = "Password") },
                onValueChange = { password = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(15.dp),
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    navController.navigate(route = Screen.Maps.route) {
                        popUpTo(route = Screen.Register.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.main_red))
            ) {
                Text(
                    text = "Kirish",
                    style = MaterialTheme.typography.body2,
                    color = Color.White
                )
            }
        }
    }
}
