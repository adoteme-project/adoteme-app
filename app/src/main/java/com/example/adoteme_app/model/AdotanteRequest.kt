package com.example.adoteme_app.model

data class AdotanteRequest(
    val nome: String,
    val email: String,
    val senha: String,
    val dtNasc: String,
    val celular: String,
    val cep: String,
    val numero: String,
    val formulario: Formulario
)