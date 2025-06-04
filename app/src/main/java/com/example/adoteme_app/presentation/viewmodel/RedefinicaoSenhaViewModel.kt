package com.example.adoteme_app.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.data.network.api.RedefinirSenhaApiService
import kotlinx.coroutines.launch

class RedefinicaoSenhaViewModel(
    private val authRepository: RedefinirSenhaApiService
) : ViewModel() {
    var email by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
    var erro by mutableStateOf<String?>(null)

    fun onEmailChange(novo: String) {
        email = novo
    }

    fun enviarCodigo(onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            erro = null
            try {
                authRepository.enviarCodigo(EmailRequest(email = email))
                onSuccess()
            } catch (e: Exception) {
                erro = "Erro ao enviar o código"
            } finally {
                isLoading = false
            }
        }
    }
}

data class EmailRequest(val email: String)