package com.uguinformatica.bluemoon.androidapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.uguinformatica.bluemoon.androidapp.theme.BlueMoon_aplicationTheme
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_dark_tertiaryContainer
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_light_secondary
import com.uguinformatica.bluemoon.androidapp.theme.md_theme_light_secondaryContainer
import com.uguinformatica.bluemoon.androidapp.ui.screens.CartScreen
import com.uguinformatica.bluemoon.androidapp.ui.screens.ForgotPasswordScreen
import com.uguinformatica.bluemoon.androidapp.ui.screens.LoginScreen
import com.uguinformatica.bluemoon.androidapp.ui.screens.OrderScreen
import com.uguinformatica.bluemoon.androidapp.ui.screens.ProductDetailScreen
import com.uguinformatica.bluemoon.androidapp.ui.screens.ProductScreen
import com.uguinformatica.bluemoon.androidapp.ui.screens.RegisterScreen
import com.uguinformatica.bluemoon.androidapp.ui.screens.SimulationScreen
import com.uguinformatica.bluemoon.androidapp.ui.screens.UserDataScreen
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.CartViewModel
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.LoginViewModel
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.SimulationViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.uguinformatica.bluemoon.androidapp.ui.viewmodels.UserDataViewModel
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueMoon_aplicationTheme {
                val simulationViewModel: SimulationViewModel by viewModels()
                val cartViewModel: CartViewModel by viewModels()
                val userViewModel: UserDataViewModel by viewModels()
                val loginViewModel: LoginViewModel by viewModels()

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val navController = rememberNavController()

                ModalNavigation(
                    drawerValue = drawerState,
                    navController = navController,
                    simulationViewModel,
                    cartViewModel,
                    userViewModel,
                    loginViewModel
                )
            }
        }
    }
}

@Composable
fun MainScaffold(
    simulationViewModel: SimulationViewModel,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    drawerState: DrawerState,
    cartViewModel: CartViewModel,
    userDataViewModel: UserDataViewModel,
    loginViewModel: LoginViewModel
) {
    var topAppBarState by remember { mutableStateOf(false) }
    var topAppBarTitle by remember { mutableStateOf("") }
    var cartButtonState by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { if (topAppBarState) MainTopAppBar(drawerState, topAppBarTitle, navController, cartButtonState) }
    ) {

        val paddingValues = it

        NavHost(navController = navController, startDestination = "LoginScreen" ) {
            composable("LoginScreen") {
                LoginScreen(navController, loginViewModel)
                topAppBarState = false
            }
            composable("RegisterScreen") {
                RegisterScreen(navController)
                topAppBarState = false
            }
            composable("ForgotPasswordScreen") {
                ForgotPasswordScreen(navController)
                topAppBarState = false
            }
            composable("UserDataScreen") {
                UserDataScreen(paddingValues, userDataViewModel)
                topAppBarTitle = "User Data"
                cartButtonState = false
                topAppBarState = true
            }
            composable("SimulationScreen") {
                SimulationScreen(paddingValues, simulationViewModel)
                topAppBarTitle = "Simulation"
                cartButtonState = false
                topAppBarState = true
            }
            composable("ProductScreen") {
                ProductScreen(paddingValues, navController)
                topAppBarTitle = "Products"
                cartButtonState = true
                topAppBarState = true
            }
            composable("OrderScreen") {
                OrderScreen(paddingValues)
                topAppBarTitle = "Orders"
                cartButtonState = false
                topAppBarState = true
            }
            composable("CartScreen") {
                CartScreen(paddingValues, cartViewModel)
                topAppBarTitle = "Cart"
                cartButtonState = false
                topAppBarState = true
            }
            composable("ProductDetailScreen/{image}/{name}/{description}/{price}",
                arguments = listOf(
                    navArgument("image") {type = NavType.IntType},
                    navArgument("name") {type = NavType.StringType},
                    navArgument("description") {type = NavType.StringType},
                    navArgument("price") {type = NavType.FloatType}
                )
            ){
                val param1 = it.arguments?.getInt("image") ?: 0
                val param2 = it.arguments?.getString("name") ?: ""
                val param3 = it.arguments?.getString("description") ?: ""
                val param4 = it.arguments?.getFloat("price") ?: 0f
                ProductDetailScreen(param1,param2,param3,param4,paddingValues,navController)
                topAppBarTitle = "Product Detail"
                cartButtonState = true
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    drawerState: DrawerState,
    topAppBarTitle: String,
    navHostController: NavHostController,
    cartButtonState: Boolean
) {

    val scope = rememberCoroutineScope()

    TopAppBar(
        title = { Text(text = topAppBarTitle, color = Color.White) },
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
            if (cartButtonState) {
                IconButton(onClick = { navHostController.navigate("CartScreen") }) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Cart",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(md_theme_light_secondary)
    )
}

@Composable
private fun ModalNavigation(
    drawerValue: DrawerState,
    navController: NavHostController,
    simulationViewModel: SimulationViewModel,
    cartViewModel: CartViewModel,
    userDataViewModel: UserDataViewModel,
    loginViewModel: LoginViewModel
) {
    val drawerState = drawerValue
    val snackbarHostState = remember { SnackbarHostState() }
    var isSelected by remember { mutableStateOf("Products") }
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
                        navController.navigate("UserDataScreen")
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
                        navController.navigate("SimulationScreen")
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
                        navController.navigate("ProductScreen")
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
                        navController.navigate("OrderScreen")
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
                Row(modifier = Modifier
                    .align(End)
                    .padding(top = 60.dp)
                    .clickable {
                        navController.navigate("LoginScreen")
                        scope.launch {
                            drawerState.apply {
                                if (isOpen) close() else open()
                            }
                        }
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "LogOut",
                        color = md_theme_light_secondaryContainer,
                        modifier = Modifier.padding(end = 15.dp)
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "Logout",
                        tint = md_theme_light_secondaryContainer
                    )

                }
            }
        }
    ) {
        MainScaffold(
            simulationViewModel,
            navController,
            snackbarHostState,
            drawerState,
            cartViewModel,
            userDataViewModel,
            loginViewModel
        )
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