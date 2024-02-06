package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ProductDetailScreen(
    image: Int,
    name: String,
    description: String,
    price: Float,
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(paddingValues)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "ProductImage",
            modifier = Modifier
                .size(300.dp)
                .padding(bottom = 40.dp).fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = name.replaceFirstChar { it.toUpperCase() },
            fontSize = 40.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Text(
            text = description,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 30.dp, bottom = 20.dp, end = 30.dp)
        )

        Text(
            text = "Price: $price$",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Row {
            Button(onClick = { navHostController.navigate("ProductScreen") }) {
                Text(text = "Go back")
            }
            Button(onClick = {  }, modifier = Modifier.padding(start = 30.dp)) {
                Text(text = "Add to the cart")
            }
        }
    }
}