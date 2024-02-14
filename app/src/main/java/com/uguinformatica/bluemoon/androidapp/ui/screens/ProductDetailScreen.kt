package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.ui.components.UiState
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.ProductDetailViewModel

@Composable
fun ProductDetailScreen(
    id: Long,
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    productDetailViewModel: ProductDetailViewModel
) {

    LaunchedEffect(key1 = {}) {
        productDetailViewModel.fetchProduct(id)
    }
    val product by productDetailViewModel.product.observeAsState(UiState.Loading())

    if (product is UiState.Loading) {
        LoadingScreen()
    } else if (product is UiState.Error) {
        return
    } else if (product is UiState.Loaded) {
        LoadedProductScreeen(
            paddingValues, (
                    product as UiState.Loaded<Product>
                    ).data, navHostController, productDetailViewModel
        )
    }
}

@Composable
private fun LoadedProductScreeen(
    paddingValues: PaddingValues,
    product: Product,
    navHostController: NavHostController,
    productDetailViewModel: ProductDetailViewModel
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {


        AsyncImage(
            model = product.image, contentDescription = "ProductImage", modifier = Modifier
                .size(300.dp)
                .padding(bottom = 40.dp)
                .fillMaxSize(), contentScale = ContentScale.Crop
        )

        Text(
            text = product.name.replaceFirstChar { it.toUpperCase() },
            fontSize = 40.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Text(
            text = product.description,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 30.dp, bottom = 20.dp, end = 30.dp)
        )

        Text(
            text = "Price: ${product.price}$",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Row {
            Button(onClick = { navHostController.navigate("ProductScreen") }) {
                Text(text = "Go back")
            }
            Button(
                onClick = { productDetailViewModel.addProductToCart(product.id, 1) },
                modifier = Modifier.padding(start = 30.dp)
            ) {
                Text(text = "Add to the cart")
            }
        }
    }
}