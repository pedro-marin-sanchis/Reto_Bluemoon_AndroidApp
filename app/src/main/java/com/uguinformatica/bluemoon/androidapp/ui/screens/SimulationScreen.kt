package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.uguinformatica.bluemoon.androidapp.domain.models.SilverType
import com.uguinformatica.bluemoon.androidapp.domain.models.Tradeable
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_light_primaryContainer
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.SimulationViewModel

@Composable
fun SimulationScreen(paddingValues: PaddingValues, simulationViewModel: SimulationViewModel) {

    val openAlertDialog by simulationViewModel.openAlertDialog.observeAsState(false)
    val openAddItemDialog by simulationViewModel.openAddItemDialog.observeAsState(false)
    val tradeableItem by simulationViewModel.tradeableItemList.observeAsState(listOf())

    when {
        openAlertDialog -> {
            AlertDialogConfirm(
                onDismissRequest = { simulationViewModel.changeOpenAlertDialog(openAlertDialog) },
                onConfirmation = {
                    simulationViewModel.changeOpenAlertDialog(openAlertDialog) },
                dialogTitle = "Confirm trade",
                simulationViewModel
            )
        }
    }

    when {
        openAddItemDialog -> {
            AddItemDialog(
                onDismissRequest = { simulationViewModel.changeOpenAddItemDialog(openAddItemDialog) },
                onConfirmation = { simulationViewModel.changeOpenAddItemDialog(openAddItemDialog) },
                simulationViewModel
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
            tradeableItem.map {
                Card {
                    Text(text = "${it.weight} | ${it.description} | ${it.sellPrice}")
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Modify")
                    }
                }
            }
            IconButton(
                onClick = { simulationViewModel.changeOpenAddItemDialog(openAddItemDialog) },
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
            onClick = { simulationViewModel.changeOpenAlertDialog(openAlertDialog) },
            modifier = Modifier.padding(top = 50.dp)
        ) {
            Text(text = "Confirm the trade")
        }
    }
}

@Composable
private fun AlertDialogConfirm(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    simulationViewModel: SimulationViewModel
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
private fun AddItemDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    simulationViewModel: SimulationViewModel,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {

        val weight by simulationViewModel.weight.observeAsState("")

        val description by simulationViewModel.description.observeAsState("")

        val sellPrice by simulationViewModel.sellPrice.observeAsState()

        val silverTypeList by simulationViewModel.silverTypeList.observeAsState(listOf(
            SilverType("Normal", 24f)
        ))

        var showSilverType by remember { mutableStateOf(false) }

        var silverType by remember { mutableStateOf(SilverType("", 0f)) }

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
            ) {
                Text(
                    text = "Add new item",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    fontSize = 23.sp,
                    textAlign = TextAlign.Center
                )

                TextField(
                    value = weight,
                    onValueChange = { simulationViewModel.setWeight(weight = it) },
                    label = { Text(text = "Weight") }
                )

                TextField(
                    value = description,
                    onValueChange = { simulationViewModel.setDescription(description = it) },
                    label = { Text(text = "Description") },
                    modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
                )

                Row(
                    modifier = Modifier
                        .clickable { showSilverType = !showSilverType }
                        .padding(start = 15.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Silver Type")

                    Spacer(modifier = Modifier.size(width = 30.dp, 0.dp))
                    Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "")

                    DropdownMenu(expanded = showSilverType, onDismissRequest = { /*TODO*/ }) {
                        silverTypeList.map {
                            DropdownMenuItem(text = {
                                Text(text = "${it.name} | ${it.currentPrice}")
                            }, onClick = { silverType = it })
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = {
                            simulationViewModel.addTradeable(Tradeable(weight.toFloat(), description, null, silverType))
                            onConfirmation()
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}