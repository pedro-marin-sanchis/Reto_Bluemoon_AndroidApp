package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_light_primaryContainer

@Composable
fun SimulationScreen(paddingValues: PaddingValues) {

    var openAlertDialog by remember { mutableStateOf(false) }
    var openAddItemDialog by remember { mutableStateOf(false) }

    when {
        openAlertDialog -> {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = {
                    openAlertDialog = false },
                dialogTitle = "Confirm trade"
            )
        }
    }

    when {
        openAddItemDialog -> {
            AddItemDialog(
                onDismissRequest = { openAddItemDialog = false },
                onConfirmation = { openAddItemDialog = false }
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(350.dp, 450.dp)
                .background(
                    color = md_theme_light_primaryContainer,
                    shape = RoundedCornerShape(30.dp)
                ),
        ) {
            IconButton(
                onClick = { openAddItemDialog = true },
                modifier = Modifier.align(Alignment.BottomEnd),
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "AddItem",
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Total: ",
                fontSize = 25.sp,
            )
            Spacer(modifier = Modifier.size(width = 250.dp, height = 0.dp))
            Icon(imageVector = Icons.Filled.CurrencyExchange, contentDescription = "Coin")
        }

        Divider(modifier = Modifier.size(350.dp,3.dp))

        Button(
            onClick = { openAlertDialog = true },
            modifier = Modifier.padding(top = 50.dp)
        ) {
            Text(text = "Confirm the trade")
        }
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
) {
    AlertDialog(
        title = { Text(
            text = dialogTitle,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        ) },
        text = { Text(text = "Are you sure to do this trade?") },
        onDismissRequest = { onDismissRequest() },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("No")
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Yes")
            }
        }
    )
}

@Composable
fun AddItemDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {

        var weight by remember { mutableStateOf("") }

        var description by remember { mutableStateOf("") }

        var sellPrice by remember { mutableStateOf("") }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Add new item",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 23.sp
                )

                TextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text(text = "Weight") }
                )

                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = "Description") },
                    modifier = Modifier.padding(top = 10.dp)
                )

                TextField(
                    value = sellPrice,
                    onValueChange = { sellPrice = it },
                    readOnly = true,
                    label = { Text(text = "Sell Price") },
                    modifier = Modifier.padding(top = 10.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        }
    }
}