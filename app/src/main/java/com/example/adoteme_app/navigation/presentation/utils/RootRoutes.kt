package com.example.adoteme_app.navigation.presentation.utils

sealed class RootRoutes(val route: String) {
    data object Login: RootRoutes("login")
    data object HomeSection: RootRoutes("main_section")
    data object UserRegistration: RootRoutes("user_registration")
    data object UserFormRegistration: RootRoutes("user_form_registration")
}