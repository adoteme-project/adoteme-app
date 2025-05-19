package com.example.adoteme_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adoteme_app.auth.presentation.login_screen.LoginScreen
import com.example.adoteme_app.auth.presentation.register_form_screen.RegistrationFormScreen
import com.example.adoteme_app.auth.presentation.register_photo_screen.RegistrationPhoneScreen
import com.example.adoteme_app.auth.presentation.register_screen.RegistrationScreen
import com.example.adoteme_app.navigation.presentation.utils.RootRoutes
import com.example.adoteme_app.ui.theme.AdotemeappTheme

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AdotemeappTheme {
                WelcomeNavGraph()
            }
        }
    }
}

@Composable
fun WelcomeNavGraph() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = RootRoutes.Login.route
    ) {
        composable(RootRoutes.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(RootRoutes.UserRegistration.route) {
            RegistrationScreen(navController = navController)
        }
        composable(RootRoutes.UserFormRegistration.route) {
            RegistrationFormScreen(navController, snackbarHostState, coroutineScope)
        }
        composable(RootRoutes.UserPhotoRegistration.route) {
            RegistrationPhoneScreen(navController, snackbarHostState, coroutineScope)
        }
    }
}