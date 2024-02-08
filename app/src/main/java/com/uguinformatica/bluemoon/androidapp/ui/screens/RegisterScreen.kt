package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uguinformatica.bluemoon.androidapp.ui.components.RegisterComponents.AddressField
import com.uguinformatica.bluemoon.androidapp.ui.components.RegisterComponents.ConfirmPasswordField
import com.uguinformatica.bluemoon.androidapp.ui.components.RegisterComponents.EmailField
import com.uguinformatica.bluemoon.androidapp.ui.components.RegisterComponents.HeaderImageRegister
import com.uguinformatica.bluemoon.androidapp.ui.components.RegisterComponents.Logged
import com.uguinformatica.bluemoon.androidapp.ui.components.RegisterComponents.NameField
import com.uguinformatica.bluemoon.androidapp.ui.components.RegisterComponents.PasswordRegisterField
import com.uguinformatica.bluemoon.androidapp.ui.components.RegisterComponents.RegisterButton
import com.uguinformatica.bluemoon.androidapp.ui.components.RegisterComponents.SurnameField
import com.uguinformatica.bluemoon.androidapp.ui.components.RegisterComponents.UsernameField
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.RegisterViewModel


@Composable
fun RegisterScreen(navHostController: NavHostController, registerViewModel: RegisterViewModel) {
    // Contenido de RegisterScreen
    var usernameReg by remember { mutableStateOf("") }
    var passwordReg by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            HeaderImageRegister(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(12.dp))

            NameField(name) { newName -> name = newName }
            Spacer(modifier = Modifier.padding(12.dp))

            SurnameField(surname) { newSurname -> surname = newSurname }
            Spacer(modifier = Modifier.padding(12.dp))

            EmailField(email) { newEmail -> email = newEmail }
            Spacer(modifier = Modifier.padding(12.dp))

            UsernameField(usernameReg) { newUsername -> usernameReg = newUsername }
            Spacer(modifier = Modifier.padding(12.dp))

            PasswordRegisterField(passwordReg) { newPassword ->
                passwordReg = newPassword
            }
            Spacer(modifier = Modifier.padding(12.dp))

            ConfirmPasswordField(confirmpassword) { newConfirmPassword ->
                confirmpassword = newConfirmPassword
            }
            Spacer(modifier = Modifier.padding(12.dp))

            AddressField(address) { newAddress -> address = newAddress }
            Spacer(modifier = Modifier.padding(12.dp))

            RegisterButton(navHostController)
            Spacer(modifier = Modifier.padding(12.dp))

            Logged(Modifier.align(Alignment.End), navHostController)

        }
    }
}