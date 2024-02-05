package com.uguinformatica.bluemoon.androidapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.LoginDto
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.BlueMoonApi
import com.uguinformatica.bluemoon.androidapp.theme.BlueMoon_aplicationTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueMoon_aplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var token = String()
                    lifecycleScope.launch {
                        token = BlueMoonApi.retrofitService.login(LoginDto("Evil0", "hola01")).token

                        println(token)

                        BlueMoonApi.retrofitService.getTrades(token).forEach{
                            println(it)

                        }

                    }

                    Text(text = token)


                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BlueMoon_aplicationTheme {
        Greeting("Android")
    }
}