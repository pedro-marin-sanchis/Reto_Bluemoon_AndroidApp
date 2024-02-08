package com.uguinformatica.bluemoon.androidapp.ui.components.HistoryTradeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uguinformatica.bluemoon.androidapp.R
import com.uguinformatica.bluemoon.androidapp.ui.screens.Trade

@Composable
fun TradeCard(trade: Trade) {
    val showDialog = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { showDialog.value = true }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bluemoonlogo),
                contentDescription = "Trade Image",
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(text = trade.date)
                Text(text = trade.address)
                Text(text = trade.item)
            }
        }
    }

    if (showDialog.value) {
        ValidationDialog(trade.isValidated) {
            showDialog.value = false
        }
    }
}