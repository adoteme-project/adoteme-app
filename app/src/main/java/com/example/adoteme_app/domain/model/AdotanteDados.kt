package com.example.adoteme_app.domain.model

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