package com.example.adoteme_app.domain.model

data class AdotantePutRequest (
    val nome: String,
    val dtNasc: String,
    val email: String,
    val celular: String,
    val numero: String
)