package com.uguinformatica.bluemoon.androidapp.ui.components.RegisterComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EmailField(user: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = user,
        onValueChange = { onTextFieldChanged(it) },
        label = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}