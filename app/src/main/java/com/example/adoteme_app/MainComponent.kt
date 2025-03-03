package com.example.adoteme_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adoteme_app.home.presentation.home_screen.HomeScreen
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes

@Composable
fun MainApp() {
    AppNavigation()
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = InternalRoutes.Home.route,
    ) {
        composable(InternalRoutes.Home.route) {
            HomeScreen(navController)
        }
        composable(InternalRoutes.Pets.route) {
            Scaffold { innerPadding ->
                Column {
                    Text(text = "Pets", modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

