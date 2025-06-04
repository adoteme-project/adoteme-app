package com.example.adoteme_app.domain.model

data class AdotanteListaRequisicaoDto(
    val idRequisicao: Long,
    val idAnimal: Long,
    val nomePet: String,
    val imagem: String,
    val dataAplicacao: String,
    val status: String,
    val motivo: String
)