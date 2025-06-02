package com.example.adoteme_app.home.presentation.utils.components

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adoteme_app.home.presentation.home_screen.HomeScreen
import com.example.adoteme_app.navigation.presentation.navi_drawer.NaviDrawerLayout
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import com.example.adoteme_app.perfil.presentation.perfil_screen.ProfileScreen
import com.example.adoteme_app.pets.presentation.favoritos_screen.AnimalFavoritoScreen
import com.example.adoteme_app.pets.presentation.ongs_screen.OngsScreen
import com.example.adoteme_app.pets.presentation.pets_screen.PetsScreen
import org.koin.androidx.compose.koinViewModel
import com.example.adoteme_app.model.PerfilViewModel
import com.example.adoteme_app.presentation.component.pages.ConfiguracoesScreen


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
                    composable(InternalRoutes.Home.route) { HomeScreen(mainNavController, nestedNavController) }
                    composable(InternalRoutes.Pets.route) { PetsScreen(mainNavController) }
                    composable(InternalRoutes.Profile.route) { ProfileScreen(mainNavController, userViewModel) }
                    composable(InternalRoutes.Favoritos.route) { AnimalFavoritoScreen(mainNavController) }
                    composable(InternalRoutes.Ongs.route) { OngsScreen(mainNavController) }

                    composable(InternalRoutes.Configuracoes.route) {
                        ConfiguracoesScreen(navController = mainNavController)
                    }
                }
            }
        }
    }
}

