package com.example.adoteme_app.presentation.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.adoteme_app.domain.model.InternalRoutes
import com.example.adoteme_app.domain.model.RootRoutes
import com.example.adoteme_app.presentation.component.pages.PerfilAplicacoScreen
import com.example.adoteme_app.presentation.component.pages.PerfilDadosScreen
import com.example.adoteme_app.presentation.component.pages.PerfilFormScreen
import com.example.adoteme_app.presentation.component.pages.PetInfoScreen
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.adoteme_app.presentation.viewmodel.PerfilViewModel
import com.example.adoteme_app.presentation.component.pages.AnimaisFiltradosScreen
import com.example.adoteme_app.presentation.component.pages.OngInfoScreen
import com.example.adoteme_app.presentation.component.pages.HabilitarTwoFactorScreen

@RequiresApi(Build.VERSION_CODES.O)
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

        composable(
            route = "petInfo/{animalId}",
            arguments = listOf(navArgument("animalId") { type = NavType.LongType })
        ) { backStackEntry ->
            val animalId = backStackEntry.arguments?.getLong("animalId") ?: return@composable
            PetInfoScreen(
                onBack = { navController.popBackStack() },
                animalId = animalId,
                navController = navController
            )
        }

        composable(
            route = "ongInfo/{ongId}",
            arguments = listOf(navArgument("ongId") { type = NavType.LongType })
        ) { backStackEntry ->
            val ongId = backStackEntry.arguments?.getLong("ongId") ?: return@composable
            OngInfoScreen(
                ongId = ongId,
                navController = navController
            )
        }

        composable(
            "animaisFiltrados/{categoriaNome}",
            arguments = listOf(navArgument("categoriaNome") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoriaNome = backStackEntry.arguments?.getString("categoriaNome") ?: ""
            AnimaisFiltradosScreen(
                categoriaNome = categoriaNome,
                navController = navController
            )
        }

        composable(InternalRoutes.ProfileData.route) {
            val userViewModel: PerfilViewModel = koinViewModel()
            val adotante by userViewModel.adotanteDados.collectAsState()
            PerfilDadosScreen(onBack = {navController.popBackStack()}, adotante, userViewModel)
        }
        composable(InternalRoutes.ProfileForm.route) {
            val userViewModel: PerfilViewModel = koinViewModel()
            PerfilFormScreen(onBack = {navController.popBackStack()}, userViewModel)
        }
        composable(InternalRoutes.ProfileAplicacoes.route) {
            PerfilAplicacoScreen(navController)
        }
        composable(InternalRoutes.HabilitarTwoFactor.route) {
            HabilitarTwoFactorScreen(navController)
        }
    }
}



