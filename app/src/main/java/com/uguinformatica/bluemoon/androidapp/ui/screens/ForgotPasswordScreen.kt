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
import com.uguinformatica.bluemoon.androidapp.ui.components.ForgotPasswordComponent.ConfirmButton
import com.uguinformatica.bluemoon.androidapp.ui.components.ForgotPasswordComponent.ConfirmNewPassword
import com.uguinformatica.bluemoon.androidapp.ui.components.ForgotPasswordComponent.NewPasswordRegisterField
import com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents.HeaderImageLogin
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.ForgotPasswordViewModel

@Composable
fun ForgotPasswordScreen(navHostController: NavHostController, forgotPasswordViewModel: ForgotPasswordViewModel) {
    var newpassword by remember { mutableStateOf("") }
    var confirmnewpassword by remember { mutableStateOf("") }

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

           NewPasswordRegisterField(newpassword) { newPassword ->
                newpassword = newPassword
            }
            Spacer(modifier = Modifier.padding(16.dp))
           ConfirmNewPassword(confirmnewpassword) { confirmNewPassword ->
                confirmnewpassword = confirmNewPassword
            }
            Spacer(modifier = Modifier.padding(16.dp))

            ConfirmButton(navHostController)
        }
    }
}


