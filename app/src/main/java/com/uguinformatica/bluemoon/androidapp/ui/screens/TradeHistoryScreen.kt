package com.uguinformatica.bluemoon.androidapp.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uguinformatica.bluemoon.androidapp.R
import com.uguinformatica.bluemoon.androidapp.domain.models.Trade
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.TradeViewModel
import java.text.SimpleDateFormat

@Composable
fun TradeHistoryScreen(tradesViewModel:TradeViewModel) {

    LaunchedEffect(key1 = {}){
        tradesViewModel.fetchTradeList()
    }

    val trades by tradesViewModel.tradesList.observeAsState(initial = emptyList())

    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(60.dp)) // Height of top app bar
        }
        items(trades) { trade ->
            TradeCard(trade)
        }
    }
}

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
                Text(text = SimpleDateFormat("dd/MM/yyyy").format(trade.date))
                Text(text = "This trade has ${if (trade.validated) "been validated" else "not been validated"}")
            }
        }
    }

    if (showDialog.value) {
        ValidationDialog(trade.validated) {
            showDialog.value = false
        }
    }
}

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