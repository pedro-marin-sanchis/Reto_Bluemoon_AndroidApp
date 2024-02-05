package com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ModifierButton(onModifyButtonClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { onModifyButtonClick() },
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text("Modify")
        }
    }
}


