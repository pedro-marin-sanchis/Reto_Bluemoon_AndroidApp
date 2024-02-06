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
    var nameData by remember { mutableStateOf("") }
    var surnameData by remember { mutableStateOf("") }
    var emailData by remember { mutableStateOf("") }
    var usernameData by remember { mutableStateOf("") }
    var passwordData by remember { mutableStateOf("") }
    var confirmpassworddata by remember { mutableStateOf("") }
    var addressData by remember { mutableStateOf("") }

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
            NameData(name = nameData, onTextFieldChanged = { nameData = it }, enabled = areFieldsEnabled)
            Spacer(modifier = Modifier.padding(12.dp))

            SurnameData(surname = surnameData, onTextFieldChanged = { surnameData = it }, enabled = areFieldsEnabled)
            Spacer(modifier = Modifier.padding(12.dp))

            EmailData(data = emailData, onTextFieldChanged = { emailData = it }, enabled = areFieldsEnabled)
            Spacer(modifier = Modifier.padding(12.dp))

            UsernameData(username = usernameData, onTextFieldChanged = { usernameData = it }, enabled = areFieldsEnabled)
            Spacer(modifier = Modifier.padding(12.dp))

            PasswordRegisterData(passwordState = passwordData, onTextFieldChanged = { passwordData = it }, enabled = areFieldsEnabled)
            Spacer(modifier = Modifier.padding(12.dp))

            ConfirmPasswordData(confirmpasswordState = confirmpassworddata, onTextFieldChanged = { confirmpassworddata = it }, enabled = areFieldsEnabled)
            Spacer(modifier = Modifier.padding(12.dp))

            AddressFieldData(address = addressData, onTextFieldChanged = { addressData = it }, enabled = areFieldsEnabled)
            Spacer(modifier = Modifier.padding(12.dp))

            // Botón para modificar
            ModifierButton(onModifyButtonClick = { areFieldsEnabled = !areFieldsEnabled })
        }
    }
}