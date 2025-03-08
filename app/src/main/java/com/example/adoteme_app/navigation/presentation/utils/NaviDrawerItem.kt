package com.example.adoteme_app.navigation.presentation.utils

sealed class NavDrawerItem(var route: String, var title: String) {
    object Home : NavDrawerItem("home", "Home")
    object Pets : NavDrawerItem("pets", "Animais")
    object Ongs : NavDrawerItem("ongs", "Ongs")
    object Favoritos : NavDrawerItem("favoritos", "Favoritos")
}

