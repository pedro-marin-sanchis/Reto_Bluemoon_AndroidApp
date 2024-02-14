package com.uguinformatica.bluemoon.androidapp.ui.screens

import android.widget.ProgressBar
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.ui.components.ProductComponents.ProductListItem
import com.uguinformatica.bluemoon.androidapp.ui.components.UiState
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.ProductViewModel


@Composable
fun ProductScreen(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    productViewModel: ProductViewModel
) {

    LaunchedEffect(key1 = {}) {
        productViewModel.fetchProducts()
    }
    val products by productViewModel.productList.observeAsState(UiState.Loading())

    if (products is UiState.Loading) {

        LoadingScreen()

    } else if (products is UiState.Error) {


    } else if (products is UiState.Loaded) {

        LoadedProductListScreen(
            (products as UiState.Loaded<List<Product>>).data,
            navHostController,
            productViewModel,
            paddingValues
        )
    }
}

@Composable
private fun LoadedProductListScreen(
    products: List<Product>,
    navHostController: NavHostController,
    productViewModel: ProductViewModel,
    paddingValues: PaddingValues
) {
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

