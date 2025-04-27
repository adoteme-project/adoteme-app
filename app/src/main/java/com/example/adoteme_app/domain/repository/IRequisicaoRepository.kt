package com.example.adoteme_app.domain.repository

import androidx.compose.runtime.State

interface IRequisicaoRepository {
    suspend fun solicitarAdocao(idAdotante: Long, idAnimal: Long): Boolean
}