package com.example.adoteme_app.model

data class Endereco (
    val id: Long,
    val cep: String,
    val rua: String,
    val bairro: String,
    val cidade: String,
    val estado: String
)