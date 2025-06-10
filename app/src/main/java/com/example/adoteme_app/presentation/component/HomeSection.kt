package com.example.adoteme_app.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.adoteme_app.domain.model.InternalRoutes
import com.example.adoteme_app.presentation.component.pages.AnimaisFiltradosScreen
import com.example.adoteme_app.presentation.component.pages.ProfileScreen
import com.example.adoteme_app.presentation.component.pages.AnimalFavoritoScreen
import com.example.adoteme_app.presentation.component.pages.OngsScreen
import com.example.adoteme_app.presentation.component.pages.PetsScreen
import org.koin.androidx.compose.koinViewModel
import com.example.adoteme_app.presentation.viewmodel.PerfilViewModel
import com.example.adoteme_app.presentation.component.pages.ConfiguracoesScreen
import com.example.adoteme_app.presentation.component.pages.HomeScreen
import com.example.adoteme_app.presentation.component.pages.OngInfoScreen


@Composable
fun HomeSectionWrapper(
    mainNavController: NavHostController
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val nestedNavController = rememberNavController()

    val userViewModel: PerfilViewModel = koinViewModel()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NaviDrawerLayout(
                    drawerState = drawerState,
                    scope = scope,
                    mainNavController = mainNavController,
                    nestedNavController = nestedNavController,
                    userViewModel = userViewModel
                )
            }
        }
    ) {
        Scaffold(
            topBar = { AdotemeTopAppBar(drawerState, scope) },
            bottomBar = {
                AdotemeBottomAppBar(
                    nestedNavController = nestedNavController,
                    userViewModel = userViewModel
                )
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                NavHost(
                    navController = nestedNavController,
                    startDestination = InternalRoutes.Home.route
                ) {
                    composable(InternalRoutes.Home.route) {
                        HomeScreen(
                            mainNavController,
                            nestedNavController
                        )
                    }
                    composable(InternalRoutes.Pets.route) { PetsScreen(mainNavController) }
                    composable(InternalRoutes.Profile.route) { ProfileScreen(mainNavController, userViewModel) }
                    composable(InternalRoutes.Favoritos.route) { AnimalFavoritoScreen(mainNavController) }
                    composable(InternalRoutes.Ongs.route) { OngsScreen(nestedNavController) }

                    composable(
                        route = "ongInfo/{ongId}",
                        arguments = listOf(navArgument("ongId") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val ongId = backStackEntry.arguments?.getLong("ongId") ?: return@composable
                        OngInfoScreen(
                            ongId = ongId,
                            navController = nestedNavController
                        )
                    }

                    composable(
                        "animaisFiltrados/{categoriaNome}",
                        arguments = listOf(navArgument("categoriaNome") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val categoriaNome = backStackEntry.arguments?.getString("categoriaNome") ?: ""
                        AnimaisFiltradosScreen(
                            categoriaNome = categoriaNome,
                            navController = mainNavController
                        )
                    }

                    composable(InternalRoutes.Configuracoes.route) {
                        ConfiguracoesScreen(navController = mainNavController)
                    }
                }
            }
        }
    }
}

