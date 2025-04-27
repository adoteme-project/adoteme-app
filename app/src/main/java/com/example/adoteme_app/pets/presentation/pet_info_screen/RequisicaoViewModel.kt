package com.example.adoteme_app.pets.presentation.pet_info_screen

import androidx.compose.runtime.State
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.auth.presentation.login_screen.LoginViewModel
import com.example.adoteme_app.domain.repository.IRequisicaoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RequisicaoViewModel(
    private val repository: IRequisicaoRepository,
    private val savedStateHandle: SavedStateHandle,
    private val loginViewModel: LoginViewModel
) : ViewModel() {

    private val _estadoAdocao = MutableStateFlow<AdocaoEstado>(AdocaoEstado.Idle)
    val estadoAdocao: StateFlow<AdocaoEstado> = _estadoAdocao

    companion object {
        private const val ID_ANIMAL_KEY = "id_animal"
    }

    val idAnimal: StateFlow<Long> = savedStateHandle.getStateFlow(ID_ANIMAL_KEY, 0L)

    fun adotarAnimal() {

        val idAdotante = loginViewModel.userId.value

        viewModelScope.launch {
            _estadoAdocao.value = AdocaoEstado.Carregando
            try {
                val idAnimalValue = idAnimal.value
                val sucesso = repository.solicitarAdocao(idAdotante, idAnimalValue)
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
