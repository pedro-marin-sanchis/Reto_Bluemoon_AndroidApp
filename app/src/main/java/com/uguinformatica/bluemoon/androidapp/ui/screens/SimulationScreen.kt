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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.uguinformatica.bluemoon.androidapp.domain.models.SilverType
import com.uguinformatica.bluemoon.androidapp.domain.models.Tradeable
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_dark_inverseOnSurface
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_dark_onSurface
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_dark_onSurfaceVariant
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_dark_outline
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_dark_surfaceVariant
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_light_inverseOnSurface
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_light_primaryContainer
import com.uguinformatica.bluemoon.androidapp.ui.components.UiState
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.SimulationViewModel

@Composable
fun SimulationScreen(paddingValues: PaddingValues, simulationViewModel: SimulationViewModel) {

    LaunchedEffect(key1 = {}){
        simulationViewModel.fetchSilverTypes()
    }

    val openAlertDialog by simulationViewModel.openAlertDialog.observeAsState(false)
    val openAddItemDialog by simulationViewModel.openAddItemDialog.observeAsState(false)
    val openModifyItemDialog by simulationViewModel.openModifyItemDialog.observeAsState(false)
    val tradeableItemList by simulationViewModel.tradeableItemList.observeAsState(listOf())
    val totalTrade by simulationViewModel.totalTrade.observeAsState(0.0)
    var alertDialogTitle by remember { mutableStateOf("") }
    val tradeableItem by simulationViewModel.tradeableItem.observeAsState(Tradeable(0.0,"",0.0, SilverType("",0.0, 0)))
    var alertDialogText by remember { mutableStateOf("") }

    when {
        openAlertDialog -> {
            AlertDialog(
                onDismissRequest = { simulationViewModel.changeOpenAlertDialog(openAlertDialog) },
                onConfirmation = {
                    if (alertDialogText != "Are you sure to do this trade?") {
                        simulationViewModel.deleteTradeable(tradeableItem!!)

                        simulationViewModel.calculateTotalTrade()
                    }

                    if (alertDialogText == "Are you sure to do this trade?") {
                        simulationViewModel.crateTrade()
                    }

                    simulationViewModel.changeOpenAlertDialog(openAlertDialog)
                },
                dialogTitle = alertDialogTitle,
                dialogText = alertDialogText,
                simulationViewModel
            )
        }
    }

    when {
        openAddItemDialog -> {
            AddItemDialog(
                onDismissRequest = {
                    simulationViewModel.setWeight("")
                    simulationViewModel.setDescription("")
                    simulationViewModel.changeOpenAddItemDialog(openAddItemDialog)
                },
                onConfirmation = {
                    simulationViewModel.setWeight("")
                    simulationViewModel.setDescription("")
                    simulationViewModel.changeOpenAddItemDialog(openAddItemDialog)
                },
                simulationViewModel
            )
        }
    }

    when {
        openModifyItemDialog -> {
            tradeableItem?.let {
                ModifyItemDialog(
                    onDismissRequest = { tradeableItem?.let {
                        simulationViewModel.changeOpenModifyItemDialog(openModifyItemDialog,
                            it
                        )
                    } },
                    onConfirmation = { tradeableItem?.let {
                        simulationViewModel.changeOpenModifyItemDialog(openModifyItemDialog,
                            it
                        )
                    } },
                    simulationViewModel,
                    it
                )
            }
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
                    color = md_theme_dark_surfaceVariant,
                    shape = RoundedCornerShape(30.dp)
                ),
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                tradeableItemList.map {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        colors = CardDefaults.cardColors(md_theme_dark_outline)
                    ) {
                        Text(
                            text = "Weight: ${it.weight} \n" +
                                    "Description: ${it.description} \n" +
                                    "Sell Price: ${it.sellPrice} \n" +
                                    "Silver Type: ${it.sliverType.name}",
                            modifier = Modifier.padding(start = 6.dp),
                            color = md_theme_light_inverseOnSurface
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            TextButton(onClick = { simulationViewModel.changeOpenModifyItemDialog(openModifyItemDialog, it) }) {
                                Text(text = "Modify")
                            }

                            IconButton(onClick = {
                                alertDialogTitle = "Delete Item"
                                alertDialogText = "Are you sure to delete this item?"
                                simulationViewModel.changeTradeable(it)
                                simulationViewModel.changeOpenAlertDialog(openAlertDialog)
                            }) {
                                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
                            }
                        }
                    }
                }
            }
            IconButton(
                onClick = {
                    simulationViewModel.setWeight("")
                    simulationViewModel.setDescription("")
                    simulationViewModel.changeOpenAddItemDialog(openAddItemDialog)
                },
                modifier = Modifier.align(Alignment.BottomEnd),
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "AddItem",
                    modifier = Modifier.fillMaxSize(),
                    tint = md_theme_dark_onSurface
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
                text = "Total: $totalTrade$",
                fontSize = 25.sp,
            )
        }

        Divider(modifier = Modifier.size(350.dp,3.dp))

        Button(
            onClick = {
                alertDialogTitle = "Confirm Trade"
                alertDialogText = "Are you sure to do this trade?"
                simulationViewModel.changeOpenAlertDialog(openAlertDialog)
            },
            modifier = Modifier.padding(top = 50.dp)
        ) {
            Text(text = "Confirm the trade")
        }
    }
}

@Composable
private fun AlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    simulationViewModel: SimulationViewModel
) {
    AlertDialog(
        title = { Text(
            text = dialogTitle,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        ) },
        text = { Text(text = dialogText) },
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
                    simulationViewModel.calculateTotalTrade()
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

        val silverTypeList by simulationViewModel.silverTypeList.observeAsState(UiState.Loading())

        val showSilverType by simulationViewModel.showSilverTypeList.observeAsState(false)

        val silverType by simulationViewModel.silverType.observeAsState(SilverType("", 0.0, 0))

        var dropDownText by remember { mutableStateOf("Select silver type") }

        if (silverTypeList is UiState.Loading) {
            return@Dialog
        } else if (silverTypeList is UiState.Error) {
            return@Dialog
        }
        val silverTypeListLoaded = (silverTypeList as UiState.Loaded).data
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
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
                        .clickable { simulationViewModel.changeShowSilverTypeList(showSilverType) }
                        .padding(start = 15.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = dropDownText)

                    Spacer(modifier = Modifier.size(width = 30.dp, 0.dp))
                    Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "")

                    DropdownMenu(expanded = showSilverType, onDismissRequest = { /*TODO*/ }) {
                        silverTypeListLoaded.map {
                            DropdownMenuItem(text = {
                                Text(text = "${it.name} | ${it.currentPrice}($/g)")
                            }, onClick = {
                                simulationViewModel.changeSilverType(it)
                                dropDownText = "${it.name} ${it.currentPrice}($/g)"
                                simulationViewModel.changeShowSilverTypeList(showSilverType)
                            })
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
                            simulationViewModel.calculateTotalTrade()
                            simulationViewModel.addTradeable(Tradeable(weight.toDouble(), description, weight.toDouble()*silverType.currentPrice, silverType))
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

@Composable
private fun ModifyItemDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    simulationViewModel: SimulationViewModel,
    tradeable: Tradeable
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {

        val weight by simulationViewModel.weight.observeAsState("${tradeable.weight}")

        val description by simulationViewModel.description.observeAsState(tradeable.description)

        //val sellPrice by simulationViewModel.sellPrice.observeAsState()

        val silverTypeList by simulationViewModel.silverTypeList.observeAsState(UiState.Loading())

        val showSilverType by simulationViewModel.showSilverTypeList.observeAsState(false)

        var dropDownText by remember { mutableStateOf("Select silver type") }

        if (silverTypeList is UiState.Loading) {
            return@Dialog
        } else if (silverTypeList is UiState.Error) {
            return@Dialog
        }

        val silverTypeListLoaded = (silverTypeList as UiState.Loaded).data

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Modify item",
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
                        .clickable { simulationViewModel.changeShowSilverTypeList(showSilverType) }
                        .padding(start = 15.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = dropDownText)

                    Spacer(modifier = Modifier.size(width = 30.dp, 0.dp))
                    Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "")

                    DropdownMenu(expanded = showSilverType, onDismissRequest = { /*TODO*/ }) {
                        silverTypeListLoaded.map {
                            DropdownMenuItem(text = {
                                Text(text = "${it.name} | ${it.currentPrice}($/g)")
                            }, onClick = {
                                simulationViewModel.changeSilverType(it)
                                dropDownText = "${it.name} ${it.currentPrice}($/g)"
                                simulationViewModel.changeShowSilverTypeList(showSilverType)
                            })
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
                        onClick = {
                            onDismissRequest()
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = {
                            simulationViewModel.modifyTradeable(tradeable)
                            simulationViewModel.calculateTotalTrade()
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