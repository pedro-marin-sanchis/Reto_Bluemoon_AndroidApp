package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.uguinformatica.bluemoon.androidapp.R
import com.uguinformatica.bluemoon.androidapp.domain.models.Order
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.ui.components.OrderComponents.OrderItem
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.OrderViewModel

@Composable
fun OrderScreen(paddingValues: PaddingValues, orderViewModel: OrderViewModel) {
    LaunchedEffect(key1 = {}){
        orderViewModel.getOrders()
    }
    val orders by orderViewModel.ordersList.observeAsState(initial = emptyList())

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(1),
        content = {
            items(orders) { order ->
                OrderItem(order = order)
            }
        },
        modifier = Modifier.padding(paddingValues),
        userScrollEnabled = true
    )
}

