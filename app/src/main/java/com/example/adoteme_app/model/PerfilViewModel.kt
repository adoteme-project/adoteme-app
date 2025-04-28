package com.example.adoteme_app.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.perfil.data.use_case.PerfilUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(
    private val perfilUseCase: PerfilUseCase
) : ViewModel() {

    private val _adotanteDados = MutableStateFlow<AdotanteDados?>(null)
    val adotanteDados: StateFlow<AdotanteDados?> = _adotanteDados

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token

    init {
        carregarUsuario()
    }

    fun carregarUsuario() {
        viewModelScope.launch {
            val adotanteSalvo = perfilUseCase.buscarAdotanteSalvo()
            val tokenSalvo = perfilUseCase.buscarTokenSalvo()
            _adotanteDados.value = adotanteSalvo
            _token.value = tokenSalvo
        }
    }

    fun logout() {
        viewModelScope.launch {
            perfilUseCase.limparDadosUsuario()
            _adotanteDados.value = null
            _token.value = null
        }
    }
}