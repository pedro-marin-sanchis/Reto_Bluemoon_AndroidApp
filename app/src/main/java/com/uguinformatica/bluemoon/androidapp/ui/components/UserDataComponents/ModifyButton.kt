package com.uguinformatica.bluemoon.androidapp.ui.components.UserDataComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
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


