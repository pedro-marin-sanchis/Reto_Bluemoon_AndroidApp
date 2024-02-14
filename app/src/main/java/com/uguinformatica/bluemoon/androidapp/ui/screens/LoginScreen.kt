package com.uguinformatica.bluemoon.androidapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents.ForgotPassword
import com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents.HeaderImageLogin
import com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents.LoginButton
import com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents.NotAccount
import com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents.PasswordField
import com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents.UserField
import com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents.CustomField
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.LoginViewModel

@Composable
fun LoginScreen(navHostController: NavHostController, loginViewModel: LoginViewModel) {
    /*var username by remember {
        mutableStateOf("")
    }*/

    val username by loginViewModel.username.observeAsState()
    val password by loginViewModel.password.observeAsState("")

    val isLoged by loginViewModel.isLoged.observeAsState(false)

    val showErrorToast by loginViewModel.showErrorToast.observeAsState(false)


    if (isLoged) {
        navHostController.navigate("ProductScreen")
    }

    if (showErrorToast) {
        Toast.makeText(
            navHostController.context,
            loginViewModel.toastMessage,
            Toast.LENGTH_SHORT
        ).show()

        loginViewModel.hideToast()

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            HeaderImageLogin(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))

            CustomField(value = username!!,
                onValueChange = { newUser -> loginViewModel.setUsername(newUser) },
                label = { Text(text = "Username") },
                enabled = true)

            Spacer(modifier = Modifier.padding(16.dp))

            PasswordField(password) { newPassword ->
                loginViewModel.setPassword(newPassword)
            }
            Spacer(modifier = Modifier.padding(16.dp))

            LoginButton(loginViewModel)
            NotAccount(Modifier, navHostController)

            Spacer(modifier = Modifier.padding(16.dp))
            ForgotPassword(Modifier.align(Alignment.End), navHostController)
        }
    }
}