package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.uguinformatica.bluemoon.androidapp.R
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.ui.components.CartComponents.CartProductItem

@Composable
fun CartScreen(paddingValues: PaddingValues) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(1),
        content = {
            items(getProducts()) { index ->
                CartProductItem(product = index)
            }
        },
        modifier = Modifier.padding(paddingValues),
        userScrollEnabled = true
    )
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