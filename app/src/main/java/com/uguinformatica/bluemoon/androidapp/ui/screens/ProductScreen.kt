package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.uguinformatica.bluemoon.androidapp.R
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.ui.components.ProductComponents.ProductListItem


@Composable
fun ProductScreen(
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        content = {
           /* getProducts().let {
                items(it) {index ->
                    ProductListItem(product = index, navHostController)
                }
            }*/
        },
        modifier = Modifier.padding(paddingValues),
        userScrollEnabled = true
    )
}

