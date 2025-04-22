package com.example.adoteme_app.model

data class DadosBancario(
    val banco: String,
    val agencia: String,
    val tipoConta: String,
    val chavePix: String,
    val nomeTitular: String,
    val qrCode: String
)
