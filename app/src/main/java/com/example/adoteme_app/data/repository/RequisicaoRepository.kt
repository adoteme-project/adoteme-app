package com.example.adoteme_app.data.repository

import androidx.compose.runtime.State
import com.example.adoteme_app.domain.repository.IRequisicaoRepository
import com.example.adoteme_app.interfaces.RequisicaoApiService
import com.example.adoteme_app.model.RequisicaoCreateDto

class RequisicaoRepository(private val api: RequisicaoApiService) : IRequisicaoRepository {
    override suspend fun solicitarAdocao(idAdotante: Long, idAnimal: Long): Boolean {
        val dto = RequisicaoCreateDto(idAdotante, idAnimal)
        val response = api.criarRequisicao(dto)
        return response.isSuccessful
    }
}
