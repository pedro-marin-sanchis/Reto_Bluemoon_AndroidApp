package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uguinformatica.bluemoon.androidapp.R
import com.uguinformatica.bluemoon.androidapp.ui.components.HistoryTradeComponents.TradeCard

@Composable
fun TradeHistoryScreen() {
    val tradeHistoryList = listOf(
        Trade("02/02/2024", "456 Elm St", item = "Ring", false),
        Trade("03/02/2024", "789 Oak St", item = "Ring", true),
        Trade("03/02/2024", "789 Oak St", item = "Ring", false),
        Trade("03/02/2024", "789 Oak St", item = "Ring", true),
        Trade("03/02/2024", "789 Oak St", item = "Ring", false),
        Trade("03/02/2024", "789 Oak St", item = "Ring", true),
        Trade("03/02/2024", "789 Oak St", item = "Ring", true),
        Trade("03/02/2024", "789 Oak St", item = "Ring",true),
        Trade("03/02/2024", "789 Oak St", item = "Ring",true),
    )

    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(60.dp)) // Height of top app bar
        }
        items(tradeHistoryList) { trade ->
            TradeCard(trade)
        }
    }
}

data class Trade(val date: String, val address: String, val item: String, val isValidated: Boolean)
