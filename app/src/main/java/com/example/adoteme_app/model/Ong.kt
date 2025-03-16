package com.example.adoteme_app.model

import androidx.compose.ui.graphics.Color

data class Ong(
    val nome: String,
    val endereco: String,
    val descricao: String,
    val distancia: String,
    val logo: Int,
    val categoriaColor: Color
)