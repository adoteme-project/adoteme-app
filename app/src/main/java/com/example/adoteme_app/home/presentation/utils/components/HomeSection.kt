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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adoteme_app.home.presentation.home_screen.HomeScreen
import com.example.adoteme_app.navigation.presentation.navi_drawer.NaviDrawerLayout
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import com.example.adoteme_app.perfil.presentation.perfil_screen.ProfileScreen
import com.example.adoteme_app.pets.presentation.pets_screen.PetsScreen

@Composable
fun HomeSectionWrapper(
    mainNavController: NavHostController,
    onInternalNavigate: (String) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val nestedNavController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NaviDrawerLayout(
                    drawerState = drawerState,
                    scope = scope,
                    mainNavController = mainNavController,
                    nestedNavController = nestedNavController
                )
            }
        }
    ) {
        Scaffold(
            topBar = { AdotemeTopAppBar(drawerState, scope) },
            bottomBar = { AdotemeBottomAppBar() }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                NavHost(
                    navController = nestedNavController,
                    startDestination = InternalRoutes.Home.route
                ) {
                    composable(InternalRoutes.Home.route) { HomeScreen() }
                    composable(InternalRoutes.Pets.route) { PetsScreen() }
                    composable(InternalRoutes.Profile.route) { ProfileScreen() }
                }
            }
        }
    }
}