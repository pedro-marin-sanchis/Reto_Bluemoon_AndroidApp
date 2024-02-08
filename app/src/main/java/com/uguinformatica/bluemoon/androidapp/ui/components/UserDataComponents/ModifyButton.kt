package com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ModifierButton(onModifyButtonClick: () -> Unit) {
    var buttonText by remember { mutableStateOf("Modify") }
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .width(200.dp)
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                buttonText = "Confirm"
                showDialog = true
            },
            modifier = Modifier.fillMaxSize()
        ) {
            Text(buttonText)
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = {
                    Text("Confirmation")
                },
                text = {
                    Column {
                        Text("¿Do you want to continue?")
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            buttonText = "Modify"
                            onModifyButtonClick()
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("Decline")
                    }
                }
            )
        }
    }
}


