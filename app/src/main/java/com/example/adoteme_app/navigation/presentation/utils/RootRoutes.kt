package com.example.adoteme_app.navigation.presentation.utils

sealed class RootRoutes(val route: String) {
    data object Login: RootRoutes("login")
    data object HomeSection: RootRoutes("home")
}