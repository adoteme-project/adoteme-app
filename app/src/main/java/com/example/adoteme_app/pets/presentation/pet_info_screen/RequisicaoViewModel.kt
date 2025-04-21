package com.example.adoteme_app.pets.presentation.pet_info_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.domain.repository.IRequisicaoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RequisicaoViewModel(private val repository: IRequisicaoRepository) : ViewModel() {

    private val _estadoAdocao = MutableStateFlow<AdocaoEstado>(AdocaoEstado.Idle)
    val estadoAdocao: StateFlow<AdocaoEstado> = _estadoAdocao

    fun adotarAnimal(idAdotante: Long, idAnimal: Long) {
        viewModelScope.launch {
            _estadoAdocao.value = AdocaoEstado.Carregando
            try {
                val sucesso = repository.solicitarAdocao(idAdotante, idAnimal)
                _estadoAdocao.value = if (sucesso) AdocaoEstado.Sucesso else AdocaoEstado.Erro("Erro ao adotar")
            } catch (e: Exception) {
                _estadoAdocao.value = AdocaoEstado.Erro(e.message ?: "Erro desconhecido")
            }
        }
    }
}

sealed class AdocaoEstado {
    object Idle : AdocaoEstado()
    object Carregando : AdocaoEstado()
    object Sucesso : AdocaoEstado()
    data class Erro(val mensagem: String) : AdocaoEstado()
}
