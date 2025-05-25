package com.example.adoteme_app.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.data.repository.AuthRepository
import com.example.adoteme_app.interfaces.RedefinirSenhaApiService
import com.example.adoteme_app.model.ResetarSenhaRequest
import kotlinx.coroutines.launch

class NovaSenhaViewModel(
    private val authRepository: RedefinirSenhaApiService
) : ViewModel() {
    var senha by mutableStateOf("")
    var confirmarSenha by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var erro by mutableStateOf<String?>(null)

    fun onSenhaChange(novo: String) {
        senha = novo
    }

    fun onConfirmarSenhaChange(novo: String) {
        confirmarSenha = novo
    }

    fun redefinirSenha(email: String, onSucesso: () -> Unit) {
        viewModelScope.launch {
            erro = null
            if (senha != confirmarSenha) {
                erro = "Senhas não coincidem"
                return@launch
            } else if (senha.length < 8) {
                erro = "Senha deve ter no mínimo 8 caracteres"
            }

            isLoading = true
            try {
                val request = ResetarSenhaRequest(email = email, newPassword = senha)
                authRepository.redefinirSenha(request)
                onSucesso()
            } catch (e: Exception) {
                erro = "Erro ao redefinir a senha"
            } finally {
                isLoading = false
            }
        }
    }
}