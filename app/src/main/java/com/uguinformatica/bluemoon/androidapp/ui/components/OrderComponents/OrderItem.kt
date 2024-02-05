package com.uguinformatica.bluemoon.androidapp.ui.components.OrderComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uguinformatica.bluemoon.androidapp.domain.models.Order

@Composable
fun OrderItem(order: Order) {
    var showProduct by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Text(
            text = "Date: ${order.date}",
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 10.dp)
        )

        Text(
            text = "Address: ${order.address}",
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 10.dp)
        )

        Row(
            modifier = Modifier
                .clickable { showProduct = !showProduct }
                .padding(start = 10.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Products",
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.size(width = 30.dp, 0.dp))
            Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "")

            DropdownMenu(
                expanded = showProduct,
                onDismissRequest = { showProduct = false }
            ) {
                order.productList.map {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Name: ${it.name} | Price: ${it.price}$") },
                        onClick = { /*TODO*/ }
                    )
                }
            }
        }

    }
}