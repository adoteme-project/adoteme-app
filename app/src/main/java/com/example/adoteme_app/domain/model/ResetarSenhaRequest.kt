package com.example.adoteme_app.domain.model

data class ResetarSenhaRequest (
    val email: String,
    val newPassword: String
)