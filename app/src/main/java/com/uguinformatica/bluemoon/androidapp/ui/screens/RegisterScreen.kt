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
import androidx.compose.runtime.livedata.observeAsState
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
    val usernameReg by registerViewModel.username.observeAsState(initial = "")
    val passwordReg by registerViewModel.password.observeAsState(initial = "")
    val confirmpassword by registerViewModel.confirmPassword.observeAsState(initial = "")
    val name by registerViewModel.name.observeAsState(initial = "")
    val surname by registerViewModel.surname.observeAsState(initial = "")
    val address by registerViewModel.address.observeAsState(initial = "")
    val email by registerViewModel.email.observeAsState(initial = "")

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

            NameField(name) { newName -> registerViewModel.setName(newName) }
            Spacer(modifier = Modifier.padding(12.dp))

            SurnameField(surname) { newSurname -> registerViewModel.setSurname(newSurname) }
            Spacer(modifier = Modifier.padding(12.dp))

            EmailField(email) { newEmail -> registerViewModel.setEmail(newEmail) }
            Spacer(modifier = Modifier.padding(12.dp))

            UsernameField(usernameReg) { newUsername -> registerViewModel.setUsername(newUsername) }
            Spacer(modifier = Modifier.padding(12.dp))

            PasswordRegisterField(passwordReg) { newPassword ->
                registerViewModel.setPassword(newPassword)
            }
            Spacer(modifier = Modifier.padding(12.dp))

            ConfirmPasswordField(confirmpassword) { newConfirmPassword ->
                registerViewModel.setConfirmPassword(newConfirmPassword)
            }
            Spacer(modifier = Modifier.padding(12.dp))

            AddressField(address) { newAddress -> registerViewModel.setAddress(newAddress) }
            Spacer(modifier = Modifier.padding(12.dp))

            RegisterButton(navHostController) { registerViewModel.register() }
            Spacer(modifier = Modifier.padding(12.dp))

            Logged(Modifier.align(Alignment.End), navHostController)

        }
    }
}