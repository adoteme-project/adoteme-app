package com.example.adoteme_app.navigation.presentation.utils

sealed class InternalRoutes(val route: String) {
    data object Home: InternalRoutes("home")
    data object Pets: InternalRoutes("pets")
    data object Ongs: InternalRoutes("ongs")
    data object Favoritos : InternalRoutes("favoritos")


    object PetsInfo : InternalRoutes("petInfo/{idAnimal}") {
        fun withId(id: Long) = "petInfo/$id"
    }

    object OngsInfo : InternalRoutes("ongInfo/{idOng}") {
        fun withId(id: Long) = "ongInfo/$id"
    }

    object CategoriaInfo : InternalRoutes("animaisFiltrados/{categoriaNome}") {
        fun withId(categoriaNome: String) = "categoria/$categoriaNome"
    }

    data object Profile: InternalRoutes("profile")
    data object ProfileData: InternalRoutes("profileData")
    data object ProfileForm: InternalRoutes("profileForm")
    data object ProfileAplicacoes: InternalRoutes("profileAplicacoes")
    data object Configuracoes: InternalRoutes("configuracoes")
    data object HabilitarTwoFactor: InternalRoutes("habilitar2fa")
}