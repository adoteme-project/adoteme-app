package com.example.adoteme_app.navigation.presentation.utils

sealed class RootRoutes(val route: String) {
    data object Login: RootRoutes("login")
    data object HomeSection: RootRoutes("main_section")
    data object UserRegistration: RootRoutes("user_registration")
    data object UserFormRegistration: RootRoutes("user_form_registration")
    data object UserPhotoRegistration: RootRoutes("user_photo_registration")
    data object RedefinicaoSenha: RootRoutes("redefinicao_senha")
    data object RedefinicaoSenhaCodigo: RootRoutes("verificar_codigo/{email}")
    data object RedefinicaoNovaSenha: RootRoutes("nova_senha/{email}")
}