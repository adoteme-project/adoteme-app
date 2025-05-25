package com.example.adoteme_app.model

data class AdotanteDados(
    val id: Long,
    val nome: String,
    val email: String,
    val dataNascimeto: String,
    val telefone: String,
    val cep: String,
    val urlFoto: String,
    val endereco: Endereco,
)