package com.example.adoteme_app.model

import androidx.compose.ui.graphics.Color

data class Ong(
    val id: Long,
    val nome: String,
    val email: String,
    val telefone: String,
    val cnpj: String,
    val descricao: String?,
    val distancia: String?,
    val endereco: Endereco,
    val dadosBancarios: DadosBancario,
    val categoriaColor: Color?,
    val imagem: String,
)