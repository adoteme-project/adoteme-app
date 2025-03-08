package com.example.adoteme_app.home.presentation.utils.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.adoteme_app.AppHomeNavigation
import com.example.adoteme_app.navigation.presentation.navi_drawer.NaviDrawerLayout

@Composable
fun HomeSection() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer (
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NaviDrawerLayout(
                    drawerState = drawerState,
                    scope = scope,
                    navController = navController
                )
            }
        }
    ) {
        Scaffold (
            topBar = { AdotemeTopAppBar(drawerState = drawerState, scope = scope) }
        ) { innerPadding ->
            AppHomeNavigation(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}