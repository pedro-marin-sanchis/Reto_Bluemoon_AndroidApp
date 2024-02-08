package com.uguinformatica.bluemoon.androidapp.ui.components.HistoryTradeComponents

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ValidationDialog(isValidated: Boolean, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Validation Status")
        },
        text = {
            Text(text = if (isValidated) "Is Validated" else "Is not Validated")
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "OK")
            }
        }
    )
}