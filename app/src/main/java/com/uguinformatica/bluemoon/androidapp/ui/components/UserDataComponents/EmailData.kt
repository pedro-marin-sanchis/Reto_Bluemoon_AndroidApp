package com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EmailData(data: String, onTextFieldChanged: (String) -> Unit, enabled: Boolean) {
    TextField(
        enabled = enabled,
        value = data,
        onValueChange = { onTextFieldChanged(it) },
        label = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}