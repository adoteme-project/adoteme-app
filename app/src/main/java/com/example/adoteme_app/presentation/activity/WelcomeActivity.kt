package com.example.adoteme_app.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.adoteme_app.presentation.component.pages.LoginScreen
import com.example.adoteme_app.presentation.component.pages.RegistrationFormScreen
import com.example.adoteme_app.presentation.component.pages.RegistrationPhoneScreen
import com.example.adoteme_app.presentation.component.pages.RegistrationScreen
import com.example.adoteme_app.domain.model.RootRoutes
import com.example.adoteme_app.presentation.component.pages.RedefinicaoNovaSenhaScreen
import com.example.adoteme_app.presentation.component.pages.RedefinicaoSenhaCodigoScreen
import com.example.adoteme_app.presentation.component.pages.RedefinicaoSenhaScreen
import com.example.adoteme_app.presentation.component.pages.TwoFactorVerificationScreen
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
            LoginScreen(
                navController = navController,
                snackbarHostState = snackbarHostState,
                coroutineScope =  coroutineScope
            )
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
        composable(RootRoutes.RedefinicaoSenha.route) {
            RedefinicaoSenhaScreen(navController)
        }
        composable(RootRoutes.RedefinicaoSenhaCodigo.route) { backStack ->
            val email = backStack.arguments?.getString("email") ?: ""
            RedefinicaoSenhaCodigoScreen(email, navController)
        }
        composable(RootRoutes.RedefinicaoNovaSenha.route) { backStack ->
            val email = backStack.arguments?.getString("email") ?: ""
            RedefinicaoNovaSenhaScreen(email, navController)
        }
        composable(
            route = "twoFactorVerification/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            TwoFactorVerificationScreen(email)
        }
    }
}