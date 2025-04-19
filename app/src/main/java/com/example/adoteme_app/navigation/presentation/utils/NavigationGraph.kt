package com.example.adoteme_app.navigation.presentation.utils

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.adoteme_app.pets.presentation.pet_info_screen.PetInfoScreen
import com.example.adoteme_app.pets.presentation.pets_screen.PetsScreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "pets"
    ) {
        composable("pets") {
            PetsScreen(navController = navController)
        }

        composable(
            route = "petInfo/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: return@composable
            PetInfoScreen(
                idAnimal = id,
                navController = navController,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
