package com.example.adoteme_app.domain.model

data class Endereco(
    val cep: String,
    val estado: String,
    val cidade: String,
    val bairro: String,
    val rua: String,
    val numero: String,
)