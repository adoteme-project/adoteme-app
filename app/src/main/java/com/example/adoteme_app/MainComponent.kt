package com.example.adoteme_app

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.adoteme_app.home.presentation.utils.components.HomeSectionWrapper
import com.example.adoteme_app.login.presentation.login_screen.LoginScreen
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import com.example.adoteme_app.navigation.presentation.utils.RootRoutes
import com.example.adoteme_app.perfil.presentation.perfilDados_screen.PerfilDadosScreen
import com.example.adoteme_app.perfil.presentation.perfilForm_screen.PerfilFormScreen
import com.example.adoteme_app.perfil.presentation.perfil_screen.ProfileScreen
import com.example.adoteme_app.pets.presentation.pet_info_screen.PetInfoScreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RootRoutes.Login.route
    ) {
        composable(RootRoutes.Login.route) {
            LoginScreen(
                onNavigate = {
                    navController.navigate(RootRoutes.HomeSection.route) {
                        popUpTo(RootRoutes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        navigation(
            route = RootRoutes.HomeSection.route,
            startDestination = InternalRoutes.Home.route
        ) {
            composable(InternalRoutes.Home.route) {
                HomeSectionWrapper(
                    mainNavController = navController,
                    onInternalNavigate = { route ->
                        navController.navigate(route)  // Handle external navigation
                    }
                )
            }
        }

        composable(InternalRoutes.PetsInfo.route) { PetInfoScreen(onBack = {navController.popBackStack()})  }
        composable(InternalRoutes.ProfileData.route) { PerfilDadosScreen(onBack = {navController.popBackStack()}) }
        composable(InternalRoutes.ProfileForm.route) { PerfilFormScreen(onBack = {navController.popBackStack()}) }
    }
}