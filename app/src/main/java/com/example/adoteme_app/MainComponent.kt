package com.example.adoteme_app

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.adoteme_app.home.presentation.utils.components.HomeSectionWrapper
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import com.example.adoteme_app.navigation.presentation.utils.RootRoutes
import com.example.adoteme_app.perfil.presentation.perfilAplicacao_screen.PerfilAplicacoScreen
import com.example.adoteme_app.perfil.presentation.perfilDados_screen.PerfilDadosScreen
import com.example.adoteme_app.perfil.presentation.perfilForm_screen.PerfilFormScreen
import com.example.adoteme_app.pets.presentation.pet_info_screen.PetInfoScreen
import com.example.adoteme_app.pets.presentation.pet_info_screen.RequisicaoViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.adoteme_app.model.PerfilViewModel
import com.example.adoteme_app.pets.presentation.AnimaisFiltradosScreen
import com.example.adoteme_app.pets.presentation.ong_info_screen.OngInfoScreen

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
    }
}



