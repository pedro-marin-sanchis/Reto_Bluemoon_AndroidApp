package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.uguinformatica.bluemoon.androidapp.R
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.ui.components.CartComponents.CartProductItem
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.CartViewModel

@Composable
fun CartScreen(paddingValues: PaddingValues, cartViewModel: CartViewModel) {

    val openAlertDialogConfirm by cartViewModel.openAlertConfirm.observeAsState(false)

    val openAlertDialogDelete by cartViewModel.openAlertDelete.observeAsState(false)

    val openDialogModifyQuantity by cartViewModel.openDialogModify.observeAsState(false)

    when {
        openAlertDialogConfirm -> {
            AlertDialogConfirm(
                onDismissRequest = { cartViewModel.closeConfirmDialog() },
                onConfirmation = { cartViewModel.closeConfirmDialog() },
                dialogTitle = "Confirm the order"
            )
        }
    }

    when {
        openAlertDialogDelete -> {
            AlertDialogDelete(
                onDismissRequest = { cartViewModel.closeDeleteDialog() },
                onConfirmation = { cartViewModel.closeDeleteDialog() },
                dialogTitle = "Delete product"
            )
        }
    }

    when {
        openDialogModifyQuantity -> {
            ModifyQuantityDialog(
                onDismissRequest = { cartViewModel.closeModifyDialog() },
                onConfirmation = { cartViewModel.closeModifyDialog() },
                cartViewModel
            )
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(1),
            content = {
                items(getProducts()) { index ->
                    CartProductItem(index, cartViewModel)
                }
                item {
                    Button(
                        onClick = { cartViewModel.openConfirmDialog() },
                        modifier = Modifier
                            .padding(start = 50.dp, end = 50.dp, bottom = 15.dp, top = 15.dp)
                    ) {
                        Text(text = "Checkout")
                    }
                }
            },
            userScrollEnabled = true
        )
    }
}

private fun getProducts(): List<Product> {
    return listOf(
        Product("Ring", "Silver Ring", 49.99f, R.drawable.bluemoonlogo),
        Product("Ring", "Silver Ring", 49.99f, R.drawable.bluemoonlogo),
        Product("Ring", "Silver Ring", 49.99f, R.drawable.bluemoonlogo),
        Product("Ring", "Silver Ring", 49.99f, R.drawable.bluemoonlogo),
        Product("Ring", "Silver Ring", 49.99f, R.drawable.bluemoonlogo),
        Product("Ring", "Silver Ring", 49.99f, R.drawable.bluemoonlogo)
    )
}

@Composable
private fun AlertDialogConfirm(
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
        text = { Text(text = "Do you want to proceed to the payment?") },
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
private fun AlertDialogDelete(
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

@Composable
private fun ModifyQuantityDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    cartViewModel: CartViewModel
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {

        val quantity by cartViewModel.quantity.observeAsState("")

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
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
                    text = "Modify the quantity of the product",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 23.sp
                )

                TextField(
                    value = quantity,
                    onValueChange = { cartViewModel.setQuantity(quantity = it) },
                    label = { Text(text = "Quantity") }
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