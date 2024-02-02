package com.uguinformatica.bluemoon.androidapp.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uguinformatica.bluemoon.androidapp.domain.models.Product

@Composable
fun ProductListItem(product: Product) {
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = product.image),
            contentDescription = "Product Image"
        )

        Text(
            text = product.name,
            fontSize = 25.sp,
        )

        Text(
            text = product.description,
            fontSize = 25.sp,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 5.dp)
            ) {
            Text(
                text = "${product.price}$",
                fontSize = 25.sp,
            )
            Spacer(modifier = Modifier.padding(horizontal = 20.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.AddShoppingCart, contentDescription = "Add to the cart")
            }
        }
    }
}