package com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.uguinformatica.bluemoon.androidapp.R

@Composable
fun HeaderImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.bluemoon),
        contentDescription = "Header Image",
        modifier = modifier
    )
}