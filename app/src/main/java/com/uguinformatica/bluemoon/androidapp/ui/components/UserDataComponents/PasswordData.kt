package com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordVisibleIconRegister(passwordIsVisible: Boolean, onToggleVisibility: () -> Unit) {
    val image = if (passwordIsVisible) {
        Icons.Default.Visibility
    } else {
        Icons.Default.VisibilityOff
    }

    IconButton(
        onClick = onToggleVisibility
    ) {
        Icon(
            imageVector = image,
            contentDescription = ""
        )
    }
}

@Composable
fun PasswordRegisterData(passwordState: String, onTextFieldChanged: (String) -> Unit, enabled: Boolean) {
    var passwordIsVisible by rememberSaveable { mutableStateOf(false) }
    val visualTransformation = if (passwordIsVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    TextField(
        enabled = enabled,
        value = passwordState,
        onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text(text = "Password") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        visualTransformation = visualTransformation,
        trailingIcon = {
            PasswordVisibleIconRegister(passwordIsVisible) {
                passwordIsVisible = !passwordIsVisible
                onTextFieldChanged(passwordState)
            }

        }
    )
}


