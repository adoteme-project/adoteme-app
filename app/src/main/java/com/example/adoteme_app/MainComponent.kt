package com.example.adoteme_app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.adoteme_app.home.presentation.utils.components.AdotemeTopAppBar
import com.example.adoteme_app.home.presentation.utils.components.HomeSection
import com.example.adoteme_app.login.presentation.login_screen.LoginScreen
import com.example.adoteme_app.navigation.presentation.navi_drawer.NaviDrawerLayout
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import com.example.adoteme_app.navigation.presentation.utils.RootRoutes
import com.example.adoteme_app.pets.presentation.ongs_screen.OngsScreen
import com.example.adoteme_app.pets.presentation.pet_info_screen.PetInfoScreen
import com.example.adoteme_app.pets.presentation.pets_screen.PetsScreen

@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RootRoutes.HomeSection.route
    ) {
        composable(RootRoutes.Login.route) {
            LoginScreen(
                onNavigate = {
                    navController.navigate(RootRoutes.HomeSection.route) {
                        popUpTo(RootRoutes.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(RootRoutes.HomeSection.route) {
            HomeSection()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AppHomeNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = InternalRoutes.Home.route,
        modifier = modifier
    ) {
        composable(InternalRoutes.Home.route) {
            HomeScreen()
        }
        composable(InternalRoutes.Pets.route) {
            PetsScreen()
        }
        composable(InternalRoutes.PetsInfo.route) {
            PetInfoScreen()
        }
        composable(InternalRoutes.Ongs.route) {
            OngsScreen()
        }
        composable(InternalRoutes.Favoritos.route) {
            //FavoritosScreen()
        }
    }
}



