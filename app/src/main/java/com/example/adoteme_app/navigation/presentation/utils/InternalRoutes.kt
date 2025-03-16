package com.example.adoteme_app.navigation.presentation.utils

sealed class InternalRoutes(val route: String) {
    data object Home: InternalRoutes("home")
    data object Pets: InternalRoutes("pets")
    data object Ongs: InternalRoutes("ongs")
    data object Favoritos: InternalRoutes("favoritos")
    data object PetsInfo: InternalRoutes("petInfo")
    data object Profile: InternalRoutes("profile")
    data object ProfileData: InternalRoutes("profileData")
    data object ProfileForm: InternalRoutes("profileForm")
    data object ProfileAplicacoes: InternalRoutes("profileAplicacoes")
}