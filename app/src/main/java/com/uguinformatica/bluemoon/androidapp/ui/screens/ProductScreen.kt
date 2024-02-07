package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uguinformatica.bluemoon.androidapp.R
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.ui.components.ProductComponents.ProductListItem
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.ProductViewModel


@Composable
fun ProductScreen(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    productViewModel: ProductViewModel
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        content = {
            getProducts().let {
                items(it) {index ->
                    ProductListItem(product = index, navHostController)
                }
            } },
        modifier = Modifier.padding(paddingValues),
        userScrollEnabled = true
    )
}

private fun getProducts(): List<Product> {
    return listOf(
        Product("Ring", "Silver Ring", 49.99f, R.drawable.anillo),
        Product("Ring", "Silver Ring", 49.99f, R.drawable.anillo),
        Product("Ring", "Silver Ring", 49.99f, R.drawable.anillo),
        Product("Ring", "Silver Ring", 49.99f, R.drawable.anillo),
        Product("Ring", "Silver Ring", 49.99f, R.drawable.anillo),
        Product("Ring", "Silver Ring", 49.99f, R.drawable.anillo)
    )
}