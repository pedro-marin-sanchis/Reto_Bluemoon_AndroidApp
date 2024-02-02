package com.uguinformatica.bluemoon.androidapp.ui.components.CartComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uguinformatica.bluemoon.androidapp.domain.models.Product

@Composable
fun CartProductItem(product: Product) {

    var openAlertDialog by remember { mutableStateOf(false) }

    when {
        openAlertDialog -> {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = {
                    openAlertDialog = false
                },
                dialogTitle = "Delete product"
            )
        }
    }

    Column {
        Card(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                .clickable { openAlertDialog = true }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = "Product Image",
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.padding(horizontal = 30.dp))

                Text(
                    text = product.name,
                    fontSize = 25.sp
                )

                Spacer(modifier = Modifier.padding(horizontal = 70.dp))

                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                )
            }
        }
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Modify Quantity")
        }
    }
}

@Composable
private fun AlertDialogExample(
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
        text = { Text(text = "Are you sure to delete this product?") },
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