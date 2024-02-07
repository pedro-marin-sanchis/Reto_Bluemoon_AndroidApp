package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uguinformatica.bluemoon.androidapp.ui.components.RegisterComponents.HeaderImageRegister
import com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents.PasswordField
import com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents.CustomField
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.UserDataViewModel

@Composable
fun UserDataScreen(paddingValues: PaddingValues, userDataViewModel: UserDataViewModel) {
    val nameData by userDataViewModel.name.observeAsState(initial = "")
    val surnameData by userDataViewModel.surname.observeAsState(initial = "")
    val emailData by userDataViewModel.email.observeAsState(initial = "")
    val usernameData by userDataViewModel.username.observeAsState(initial = "")
    val passwordData by userDataViewModel.password.observeAsState(initial = "")
    val confirmPasswordData by userDataViewModel.confirmPassword.observeAsState(initial = "")
    val addressData by userDataViewModel.address.observeAsState(initial = "")




    val areFieldsEnabled by userDataViewModel.areFieldsEnabled.observeAsState(initial = false)

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

            Text(text = "Personal Information", modifier = Modifier.padding(12.dp))
            Spacer(modifier = Modifier.padding(12.dp))

            // Otros campos de texto
            CustomField(
                value = nameData,
                onValueChange = { userDataViewModel.setName(it) },
                label = { Text("Name") },
                enabled = areFieldsEnabled
            )
            Spacer(modifier = Modifier.padding(12.dp))

            CustomField(
                value = surnameData,
                onValueChange = { userDataViewModel.setSurname(it) },
                label = { Text("Surnames") },
                enabled = areFieldsEnabled
            )
            Spacer(modifier = Modifier.padding(12.dp))

            CustomField(
                value = emailData,
                onValueChange = { userDataViewModel.setEmail(it) },
                label = { Text("Email") },
                enabled = areFieldsEnabled
            )
            Spacer(modifier = Modifier.padding(12.dp))

            CustomField(
                value = usernameData,
                onValueChange = { userDataViewModel.setUsername(it) },
                label = { Text("Username") },
                enabled = areFieldsEnabled
            )
            Spacer(modifier = Modifier.padding(12.dp))

            CustomField(
                value = addressData,
                onValueChange = { userDataViewModel.setAddress(it) },
                label = { Text("Address") },
                enabled = areFieldsEnabled
            )
            Spacer(modifier = Modifier.padding(12.dp))

            Button(onClick = {
                if (areFieldsEnabled) {
                    userDataViewModel.updateUser()
                } else {
                    userDataViewModel.enableModify()
                }
            }) {
                
                Text(if (areFieldsEnabled) "Save" else "Modify")

            }

            Spacer(modifier = Modifier.padding(12.dp))

            Divider()


            Spacer(modifier = Modifier.padding(12.dp))

            Text(text = "Change Password", modifier = Modifier.padding(12.dp))

            Spacer(modifier = Modifier.padding(12.dp))

            PasswordField(
                passwordState = passwordData,
                onTextFieldChanged = { userDataViewModel.setPassword(it) },
                enabled = areFieldsEnabled,
                placeholder = { Text("Password") }
            )
            Spacer(modifier = Modifier.padding(12.dp))

            PasswordField(
                passwordState = confirmPasswordData,
                onTextFieldChanged = { userDataViewModel.setConfirmPassword(it) },
                enabled = areFieldsEnabled,
                placeholder = { Text("Confirm Password") }
            )

            Spacer(modifier = Modifier.padding(12.dp))



            // Botón para modificar

            Button(onClick = {
                if (areFieldsEnabled) {
                    userDataViewModel.updateUser()
                } else {
                    userDataViewModel.enableModify()
                }
            }) {
                Text(if (areFieldsEnabled) "Save" else "Change Password")

            }

            /*ModifierButton(onModifyButtonClick = {
                if (areFieldsEnabled) {
                    userDataViewModel.updateUser()
                } else {
                    userDataViewModel.enableModify()
                }
            })*/
        }
    }
}
