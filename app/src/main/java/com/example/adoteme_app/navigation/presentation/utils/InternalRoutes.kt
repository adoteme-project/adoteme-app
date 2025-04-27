package com.example.adoteme_app.navigation.presentation.utils

sealed class InternalRoutes(val route: String) {
    data object Home: InternalRoutes("home")
    data object Pets: InternalRoutes("pets")
    data object Ongs: InternalRoutes("ongs")

    object Favoritos : InternalRoutes("favoritos/{idUser}") {
        fun withAdotanteId(adotanteId: Long) = "favoritos/$adotanteId"
    }

    object PetsInfo : InternalRoutes("petInfo/{idAnimal}") {
        fun withId(id: Long) = "petInfo/$id"
    }

    data object Profile: InternalRoutes("profile")
    data object ProfileData: InternalRoutes("profileData")
    data object ProfileForm: InternalRoutes("profileForm")
    data object ProfileAplicacoes: InternalRoutes("profileAplicacoes")
}