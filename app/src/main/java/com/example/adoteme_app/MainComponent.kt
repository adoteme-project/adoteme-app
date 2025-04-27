package com.example.adoteme_app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.adoteme_app.home.presentation.utils.components.HomeSectionWrapper
import com.example.adoteme_app.auth.presentation.login_screen.LoginScreen
import com.example.adoteme_app.auth.presentation.login_screen.LoginViewModel
import com.example.adoteme_app.auth.presentation.register_form_screen.RegistrationFormScreen
import com.example.adoteme_app.auth.presentation.register_screen.RegistrationScreen
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import com.example.adoteme_app.navigation.presentation.utils.RootRoutes
import com.example.adoteme_app.perfil.presentation.perfilAplicacao_screen.PerfilAplicacoScreen
import com.example.adoteme_app.perfil.presentation.perfilDados_screen.PerfilDadosScreen
import com.example.adoteme_app.perfil.presentation.perfilForm_screen.PerfilFormScreen
import com.example.adoteme_app.pets.presentation.pet_info_screen.PetInfoScreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RootRoutes.HomeSection.route
    ) {

        navigation(
            route = RootRoutes.HomeSection.route,
            startDestination = InternalRoutes.Home.route
        ) {
            composable(InternalRoutes.Home.route) {
                HomeSectionWrapper(
                    mainNavController = navController,
                )
            }
        }

        composable(route = InternalRoutes.PetsInfo.route) { backStackEntry ->
            val idAnimal = backStackEntry.arguments?.getLong("idAnimal")?.toLong() ?: 0

            PetInfoScreen(
                idAnimal = idAnimal,
                onBack = { navController.popBackStack() },
                navController = navController
            )
        }
        composable(InternalRoutes.ProfileData.route) { PerfilDadosScreen(onBack = {navController.popBackStack()}) }
        composable(InternalRoutes.ProfileForm.route) { PerfilFormScreen(onBack = {navController.popBackStack()}) }
        composable(InternalRoutes.ProfileAplicacoes.route) { PerfilAplicacoScreen(navController) }
    }
}



