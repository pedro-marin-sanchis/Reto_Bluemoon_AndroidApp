package com.uguinformatica.bluemoon.androidapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.uguinformatica.bluemoon.androidapp.theme.BlueMoon_aplicationTheme
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_dark_tertiaryContainer
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_light_secondary
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_light_secondaryContainer
import com.uguinformatica.bluemoon.androidapp.ui.screens.OrderScreen
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.SimulationViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueMoon_aplicationTheme {
                val simulationViewModel: SimulationViewModel by viewModels()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val navController = rememberNavController()

                MyModalNavigation(drawerValue = drawerState, navController = navController, simulationViewModel)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyScaffold(simulationViewModel: SimulationViewModel, navController: NavController, snackbarHostState: SnackbarHostState, drawerState: DrawerState) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MyTopAppBar(drawerState) }
    ) {
        //SimulationScreen(it, simulationViewModel)
        //ProductScreen(it)
        OrderScreen(paddingValues = it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()

    TopAppBar(
        title = { Text(text = "Simulation", color = Color.White) },
        navigationIcon = { 
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bluemoonlogo),
                    contentDescription = "Cart",
                    modifier = Modifier.size(50.dp))
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Cart",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(md_theme_light_secondary)
    )
}

@Composable
private fun MyModalNavigation(drawerValue: DrawerState, navController: NavController, simulationViewModel: SimulationViewModel) {
    val drawerState = drawerValue
    val snackbarHostState = remember { SnackbarHostState() }
    var isSelected by remember { mutableStateOf("User Data") }
    val scope = rememberCoroutineScope()


    ModalNavigationDrawer(
        gesturesEnabled = false,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = md_theme_light_secondary) {
                Image(
                    painter = painterResource(id = R.drawable.slogan_blue_moon),
                    contentDescription = "",
                    modifier = Modifier.size(400.dp,250.dp),
                    contentScale = ContentScale.FillBounds)
                Spacer(modifier = Modifier.size(0.dp,10.dp))
                NavigationDrawerItem(
                    modifier = Modifier.padding(10.dp),
                    icon = { Icon(imageVector = Icons.Default.House, contentDescription = "UserData") },
                    label = { Text(text = "User Data") },
                    selected =  isSelected == "User Data",
                    onClick = {
                        isSelected = "User Data"
                        navController.navigate("UserData")
                        scope.launch {
                            drawerState.apply {
                                if (isOpen) close() else open()
                            }
                        }
                    },
                    colors = drawerItemColors()
                )
                NavigationDrawerItem(
                    modifier = Modifier.padding(10.dp),
                    icon = { Icon(imageVector = Icons.Default.CurrencyExchange, contentDescription = "Simulation") },
                    label = { Text(text = "Simulator") },
                    selected = isSelected == "Simulator",
                    onClick = {
                        isSelected = "Simulator"
                        navController.navigate("SimulatorScreen")
                        scope.launch {
                            drawerState.apply {
                                if (isOpen) close() else open()
                            }
                        }
                    },
                    colors = drawerItemColors()
                )
                NavigationDrawerItem(
                    modifier = Modifier.padding(10.dp),
                    icon = { Icon(imageVector = Icons.Default.Shop, contentDescription = "Products") },
                    label = { Text(text = "Products") },
                    selected = isSelected == "Products",
                    onClick = {
                        isSelected = "Products"
                        navController.navigate("ProductsScreen")
                        scope.launch {
                            drawerState.apply {
                                if (isOpen) close() else open()
                            }
                        }
                    },
                    colors = drawerItemColors()
                )
                NavigationDrawerItem(
                    modifier = Modifier.padding(10.dp),
                    icon = { Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "Orders") },
                    label = { Text(text = "Orders") },
                    selected = isSelected == "Orders",
                    onClick = {
                        isSelected = "Orders"
                        navController.navigate("OrdersScreen")
                        scope.launch {
                            drawerState.apply {
                                if (isOpen) close() else open()
                            }
                        }
                    },
                    colors = drawerItemColors()
                )
                NavigationDrawerItem(
                    modifier = Modifier.padding(10.dp),
                    icon = { Icon(imageVector = Icons.Default.Close, contentDescription = "Close") },
                    label = { Text(text = "Close") },
                    selected = isSelected == "Close",
                    onClick = {
                        isSelected = ""
                        scope.launch {
                            drawerState.apply {
                                if (isOpen) close() else open()
                            }
                        }
                    },
                    colors = drawerItemColors()
                )
            }
        }
    ) {
        MyScaffold(simulationViewModel, navController, snackbarHostState, drawerState )
    }
}

@Composable
fun drawerItemColors(): NavigationDrawerItemColors {
    return NavigationDrawerItemDefaults.colors(
        unselectedContainerColor = md_theme_dark_tertiaryContainer,
        selectedContainerColor = md_theme_light_secondaryContainer,
        selectedIconColor = md_theme_dark_tertiaryContainer,
        unselectedIconColor = md_theme_light_secondaryContainer,
        selectedTextColor = md_theme_dark_tertiaryContainer,
        unselectedTextColor = md_theme_light_secondaryContainer
    )
}