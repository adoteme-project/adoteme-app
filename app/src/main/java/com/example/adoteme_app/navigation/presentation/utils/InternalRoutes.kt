package com.example.adoteme_app.navigation.presentation.utils

sealed class InternalRoutes(val route: String) {
    object Home: InternalRoutes("home")
    object Pets: InternalRoutes("pets")
    object Doacoes: InternalRoutes("doacoes")
    object Ongs: InternalRoutes("ongs")
}