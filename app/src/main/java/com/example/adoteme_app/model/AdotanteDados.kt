package com.example.adoteme_app.model

data class AdotanteDados(
    val nome: String,
    val email: String,
    val dataNascimeto: String,
    val telefone: String,
    val cep: String,
    val numero: String,
    val urlFoto: String,
    val endereco: Endereco
)