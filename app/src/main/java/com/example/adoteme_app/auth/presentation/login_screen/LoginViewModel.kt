package com.example.adoteme_app.auth.presentation.login_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.auth.presentation.login_screen.auth_service.request.LoginRequest
import com.example.adoteme_app.interfaces.AuthApiService
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(
    private val api: AuthApiService
) : ViewModel() {

    private val _token = MutableStateFlow("")
    val token: StateFlow<String> = _token

    private val _userId = MutableStateFlow(0L)
    val userId: StateFlow<Long> = _userId

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = api.login(LoginRequest(email, password))

                _token.value = response.token
                _userId.value = response.idUser

                _isLoggedIn.value = true

                Log.i("LoginViewModel", "Login bem-sucedido: token=${_token.value}, idUser=${_userId.value}")
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Erro ao tentar autenticar", e)
                _isLoggedIn.value = false
            }
        }
    }
}



