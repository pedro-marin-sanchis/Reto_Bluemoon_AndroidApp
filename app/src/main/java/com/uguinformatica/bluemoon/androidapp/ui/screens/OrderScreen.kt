package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.uguinformatica.bluemoon.androidapp.R
import com.uguinformatica.bluemoon.androidapp.domain.models.Order
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.ui.components.OrderComponents.OrderItem
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.OrderViewModel

@Composable
fun OrderScreen(paddingValues: PaddingValues, orderViewModel: OrderViewModel) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(1),
        content = {
            items(getOrders()) {index ->
                OrderItem(order = index)
            }
        },
        modifier = Modifier.padding(paddingValues),
        userScrollEnabled = true
    )
}

fun getOrders(): List<Order> {
    return listOf(
        Order("12/12/2012","C/Example Nº21", getProductsOrders()),
        Order("12/12/2012","C/Example Nº21", getProductsOrders()),
        Order("12/12/2012","C/Example Nº21", getProductsOrders()),
        Order("12/12/2012","C/Example Nº21", getProductsOrders()),
        Order("12/12/2012","C/Example Nº21", getProductsOrders()),
        Order("12/12/2012","C/Example Nº21", getProductsOrders()),
        Order("12/12/2012","C/Example Nº21", getProductsOrders()),
    )
}

fun getProductsOrders(): List<Product> {
    return listOf(
        Product("Ring", "Silver Ring", 49.99f, R.drawable.bluemoonlogo),
        Product("Ring", "Silver Ring", 49.99f, R.drawable.bluemoonlogo),
        Product("Ring", "Silver Ring", 49.99f, R.drawable.bluemoonlogo)
    )
}