package com.example.adoteme_app.model

data class ValidacaoCodigoRequest (
    val email: String,
    val verificationCode: String,
)