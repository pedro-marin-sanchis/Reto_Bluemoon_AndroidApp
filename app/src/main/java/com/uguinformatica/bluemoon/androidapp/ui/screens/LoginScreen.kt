package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uguinformatica.bluemoon.androidapp.R
import com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents.ForgotPassword
import com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents.HeaderImageLogin
import com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents.LoginButton
import com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents.NotAccount
import com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents.PasswordField
import com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents.UserField

@Composable
fun LoginScreen() {
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

            LoginButton()
            NotAccount()

            Spacer(modifier = Modifier.padding(16.dp))
            ForgotPassword(Modifier.align(Alignment.End))
        }
    }
}