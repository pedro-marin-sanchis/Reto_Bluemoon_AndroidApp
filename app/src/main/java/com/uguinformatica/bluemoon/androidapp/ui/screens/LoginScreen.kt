package com.uguinformatica.bluemoon.androidapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uguinformatica.bluemoon.androidapp.R

@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            UserField(username) { newUsername -> username = newUsername }
            Spacer(modifier = Modifier.padding(16.dp))
            PasswordField(password) { newPassword ->
                password = newPassword
            }
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton()
            Spacer(modifier = Modifier.padding(16.dp))
            ForgotPassword(Modifier.align(Alignment.End))
        }
    }
}

@Composable
fun LoginButton() {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { /* Do something! */ },
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text("Login")
        }
    }
}

@Composable
fun ForgotPassword(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        // Contenido de ForgotPassword
        Text(
            text = "Forgot Password?",
            modifier = Modifier.clickable { },
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1834CE)
        )
    }
}

@Composable
fun HeaderImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.bluemoon),
        contentDescription = "Header Image",
        modifier = modifier
    )
}

@Composable
fun UserField(user: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = user,
        onValueChange = { onTextFieldChanged(it) },
        label = { Text(text = "Username") },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PasswordVisibleIcon(passwordIsVisible: Boolean, onToggleVisibility: () -> Unit) {
    val image = if (passwordIsVisible) {
        Icons.Default.VisibilityOff
    } else {
        Icons.Default.Visibility
    }

    IconButton(
        onClick = onToggleVisibility
    ) {
        Icon(
            imageVector = image,
            contentDescription = ""
        )
    }
}

@Composable
fun PasswordField(passwordState: String, onTextFieldChanged: (String) -> Unit) {
    var passwordIsVisible by rememberSaveable { mutableStateOf(false) }
    val visualTransformation = if (passwordIsVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    TextField(
        value = passwordState,
        onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text(text = "Password") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (passwordState.isNotBlank()) {
                PasswordVisibleIcon(passwordIsVisible) {
                    passwordIsVisible = !passwordIsVisible
                    onTextFieldChanged(passwordState)
                }
            } else null
        }
    )
}

