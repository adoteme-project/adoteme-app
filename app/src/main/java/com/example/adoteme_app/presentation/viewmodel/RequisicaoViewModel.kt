package com.example.adoteme_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.data.network.api.RequisicaoApiService
import com.example.adoteme_app.domain.model.RequisicaoCreateDto
import com.example.adoteme_app.domain.model.StatusRequisicao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RequisicaoViewModel(
    private val requisicaoApiService: RequisicaoApiService
) : ViewModel() {

    private val _statusRequisicao = MutableStateFlow<StatusRequisicao>(StatusRequisicao.Nada)
    val statusRequisicao: StateFlow<StatusRequisicao> = _statusRequisicao

    fun criarRequisicao(userId: Long, animalId: Long) {
        viewModelScope.launch {
            try {
                _statusRequisicao.value = StatusRequisicao.Carregando

                val requisicaoDto = RequisicaoCreateDto(
                    idAdotante = userId,
                    idAnimal = animalId
                )

                val response = requisicaoApiService.criarRequisicao(requisicaoDto)

                _statusRequisicao.value = when {
                    response.isSuccessful -> StatusRequisicao.Sucesso
                    response.code() == 500 -> StatusRequisicao.RequisicaoDuplicada
                    else -> StatusRequisicao.Erro("Erro ao processar a requisição")
                }

            } catch (e: Exception) {
                _statusRequisicao.value = StatusRequisicao.Erro(e.message ?: "Erro desconhecido")
            }
        }
    }
}
