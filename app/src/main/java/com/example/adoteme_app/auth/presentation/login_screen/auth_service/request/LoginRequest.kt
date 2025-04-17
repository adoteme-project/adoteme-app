package com.example.adoteme_app.auth.presentation.login_screen.auth_service.Request

class LoginRequest {
    var email: String? = null
    var senha: String? = null

    constructor()

    constructor(email: String?, senha: String?) {
        this.email = email
        this.senha = senha
    }
}