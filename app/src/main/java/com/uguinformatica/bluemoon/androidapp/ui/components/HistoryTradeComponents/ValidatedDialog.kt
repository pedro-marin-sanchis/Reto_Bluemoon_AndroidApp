package com.uguinformatica.bluemoon.androidapp.ui.components.HistoryTradeComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ValidationDialog(isValidated: Boolean, itemsOrdered: List<String>, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Validation Status")
        },
        text = {
            Column {
                Text(text = if (isValidated) "Is Validated" else "Is not Validated")
                Text(text = "Items Ordered:")
                itemsOrdered.forEach { item ->
                    Text(text = "- $item")
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "OK")
            }
        }
    )
}