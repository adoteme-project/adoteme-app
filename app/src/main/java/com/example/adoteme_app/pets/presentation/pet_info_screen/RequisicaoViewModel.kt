package com.example.adoteme_app.pets.presentation.pet_info_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.interfaces.RequisicaoApiService
import com.example.adoteme_app.model.AdotanteListaRequisicaoDto
import com.example.adoteme_app.model.RequisicaoCreateDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

                requisicaoApiService.criarRequisicao(requisicaoDto)

                _statusRequisicao.value = StatusRequisicao.Sucesso
            } catch (e: Exception) {
                _statusRequisicao.value = StatusRequisicao.Erro(e.message ?: "Erro desconhecido")
            }
        }
    }
}
