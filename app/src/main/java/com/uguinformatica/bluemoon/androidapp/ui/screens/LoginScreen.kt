package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun LoginScreen(navHostController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            UserField(username) { newUsername -> username = newUsername }
            Spacer(modifier = Modifier.padding(16.dp))
            PasswordField(password) { newPassword ->
                password = newPassword
            }
            Spacer(modifier = Modifier.padding(16.dp))

            LoginButton(navHostController)
            NotAccount(Modifier,navHostController)

            Spacer(modifier = Modifier.padding(16.dp))
            ForgotPassword(Modifier.align(Alignment.End), navHostController)
        }
    }
}