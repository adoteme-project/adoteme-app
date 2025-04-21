package com.example.adoteme_app.domain.repository

interface IRequisicaoRepository {
    suspend fun solicitarAdocao(idAdotante: Long, idAnimal: Long): Boolean
}