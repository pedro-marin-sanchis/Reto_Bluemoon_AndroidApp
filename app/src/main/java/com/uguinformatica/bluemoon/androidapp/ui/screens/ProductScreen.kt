package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.uguinformatica.bluemoon.androidapp.ui.components.ProductComponents.ProductListItem
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.ProductViewModel


@Composable
fun ProductScreen(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    productViewModel: ProductViewModel
) {
    val products by productViewModel.productList.observeAsState(initial = emptyList())
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        content = {
            items(products) { index ->
                ProductListItem(product = index, navHostController, productViewModel)
            }
        },
        modifier = Modifier.padding(paddingValues),
        userScrollEnabled = true
    )
}

