package com.example.adoteme_app.domain.model

data class ValidacaoCodigoRequest (
    val email: String,
    val verificationCode: String,
)