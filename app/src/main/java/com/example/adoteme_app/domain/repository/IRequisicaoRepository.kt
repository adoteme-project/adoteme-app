package com.example.adoteme_app.domain.repository

interface IRequisicaoRepository {
    suspend fun solicitarAdocao(idAdotante: Int, idAnimal: Int): Boolean
}