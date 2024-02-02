package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.uguinformatica.bluemoon.androidapp.R
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.ui.components.CartComponents.CartProductItem

@Composable
fun CartScreen(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(1),
            content = {
                items(getProducts()) { index ->
                    CartProductItem(product = index)
                }
                item {
                    Button(onClick = { /*TODO*/ }) {
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