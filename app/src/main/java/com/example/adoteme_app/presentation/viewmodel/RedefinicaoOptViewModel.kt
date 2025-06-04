package com.example.adoteme_app.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.data.network.api.RedefinirSenhaApiService
import com.example.adoteme_app.domain.model.ValidacaoCodigoRequest
import kotlinx.coroutines.launch

class RedefinicaoOptViewModel(
    private val authRepository: RedefinirSenhaApiService
) : ViewModel() {
    var codigo by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
    var erro by mutableStateOf<String?>(null)

    fun onCodigoChange(novoCodigo: String) {
        if (novoCodigo.length <= 6) codigo = novoCodigo
    }

    fun validarCodigo(email: String, onSucesso: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            erro = null
            try {
                val request = ValidacaoCodigoRequest(email = email, verificationCode = codigo)
                val valido = authRepository.validarCodigo(request)
                if (valido) onSucesso()
                else erro = "Código inválido"
            } catch (e: Exception) {
                erro = "Erro ao validar o código"
            } finally {
                isLoading = false
            }
        }
    }
}