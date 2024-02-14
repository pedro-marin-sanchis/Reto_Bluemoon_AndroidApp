package com.uguinformatica.bluemoon.androidapp.ui.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import com.uguinformatica.bluemoon.androidapp.ui.components.OrderComponents.OrderItem
import com.uguinformatica.bluemoon.androidapp.ui.components.UiState
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.OrderViewModel

@Composable
fun OrderScreen(paddingValues: PaddingValues, orderViewModel: OrderViewModel) {
    LaunchedEffect(key1 = {}){
        orderViewModel.getOrders()
    }
    val orders by orderViewModel.ordersList.observeAsState(UiState.Loading())
    val toastMessage by orderViewModel.toastMessage.observeAsState(initial = "")

    val context = LocalContext.current

    LaunchedEffect(key1 = toastMessage){
        if(toastMessage.isNotEmpty()){
            Toast.makeText(
                context,
                toastMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    if (orders is UiState.Loading) {
        LoadingScreen()
    } else if (orders is UiState.Error) {

    } else if (orders is UiState.Loaded) {

        val orders = (orders as UiState.Loaded).data

        if (orders.isEmpty()) {
            return
        }

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

}

