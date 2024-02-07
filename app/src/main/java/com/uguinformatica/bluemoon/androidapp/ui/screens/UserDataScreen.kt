package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uguinformatica.bluemoon.androidapp.ui.components.RegisterComponents.HeaderImageRegister
import com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents.AddressFieldData
import com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents.ConfirmPasswordData
import com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents.EmailData
import com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents.ModifierButton
import com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents.NameData
import com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents.PasswordRegisterData
import com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents.SurnameData
import com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents.UsernameData
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.UserDataViewModel

@Composable
fun UserDataScreen(paddingValues: PaddingValues, userDataViewModel: UserDataViewModel) {
    val nameData by userDataViewModel.name.observeAsState(initial = "")
    val surnameData by userDataViewModel.surname.observeAsState(initial = "")
    val emailData by userDataViewModel.email.observeAsState(initial = "")
    val usernameData by userDataViewModel.username.observeAsState(initial = "")
    val passwordData by userDataViewModel.password.observeAsState(initial = "")
    val confirmpassworddata by userDataViewModel.confirmPassword.observeAsState(initial = "")
    val addressData by userDataViewModel.address.observeAsState(initial = "")

    userDataViewModel.fetchUserData()


    var areFieldsEnabled by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            HeaderImageRegister(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(12.dp))

            // Otros campos de texto
            NameData(name = nameData, onTextFieldChanged = {
                userDataViewModel.setName(it)
            }, enabled = areFieldsEnabled)
            Spacer(modifier = Modifier.padding(12.dp))

            SurnameData(surname = surnameData, onTextFieldChanged = {
                userDataViewModel.setSurname(it)
            }, enabled = areFieldsEnabled)
            Spacer(modifier = Modifier.padding(12.dp))

            EmailData(
                data = emailData,
                onTextFieldChanged = { userDataViewModel.setEmail(it) },
                enabled = areFieldsEnabled
            )
            Spacer(modifier = Modifier.padding(12.dp))

            UsernameData(
                username = usernameData,
                onTextFieldChanged = { userDataViewModel.setUsername(it) },
                enabled = areFieldsEnabled
            )
            Spacer(modifier = Modifier.padding(12.dp))

            PasswordRegisterData(
                passwordState = passwordData,
                onTextFieldChanged = { userDataViewModel.setPassword(it) },
                enabled = areFieldsEnabled
            )
            Spacer(modifier = Modifier.padding(12.dp))

            ConfirmPasswordData(
                confirmpasswordState = confirmpassworddata,
                onTextFieldChanged = { userDataViewModel.setConfirmPassword(it) },
                enabled = areFieldsEnabled
            )
            Spacer(modifier = Modifier.padding(12.dp))

            AddressFieldData(
                address = addressData,
                onTextFieldChanged = { userDataViewModel.setAddress(it) },
                enabled = areFieldsEnabled
            )
            Spacer(modifier = Modifier.padding(12.dp))

            // Botón para modificar
            ModifierButton(onModifyButtonClick = { areFieldsEnabled = !areFieldsEnabled })
        }
    }
}