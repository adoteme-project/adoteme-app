package com.example.adoteme_app.navigation.presentation.utils

sealed class InternalRoutes(val route: String) {
    data object Home: InternalRoutes("home")
    data object Pets: InternalRoutes("pets")
    data object Ongs: InternalRoutes("ongs")
    data object Favoritos: InternalRoutes("favoritos")
    data object PetsInfo: InternalRoutes("petInfo")
}