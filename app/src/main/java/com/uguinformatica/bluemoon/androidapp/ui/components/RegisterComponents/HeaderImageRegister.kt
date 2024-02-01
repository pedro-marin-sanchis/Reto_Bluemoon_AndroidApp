package com.uguinformatica.bluemoon.androidapp.ui.components.RegisterComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uguinformatica.bluemoon.androidapp.R

@Composable
fun HeaderImageRegister(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.bluemoon),
        contentDescription = "Header Image",
        modifier = modifier
            .width(150.dp)
            .height(150.dp)
    )
}
