package com.uguinformatica.bluemoon.androidapp.ui.components.LoginComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun NotAccount(modifier: Modifier = Modifier, navHostController: NavHostController) {
    Box(
        modifier = modifier
    ) {
        // Contenido de ForgotPassword
        Text(
            text = "You Don't have an account? Register now",
            modifier = Modifier.clickable { navHostController.navigate("RegisterScreen") },
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}