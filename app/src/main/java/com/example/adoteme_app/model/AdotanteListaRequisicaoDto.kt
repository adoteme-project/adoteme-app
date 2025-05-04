package com.example.adoteme_app.model

import java.time.LocalDateTime

data class AdotanteListaRequisicaoDto(
    val idRequisicao: Long,
    val idAnimal: Long,
    val nomePet: String,
    val imagem: String,
    val dataAplicacao: String,
    val status: String,
    val motivo: String
)