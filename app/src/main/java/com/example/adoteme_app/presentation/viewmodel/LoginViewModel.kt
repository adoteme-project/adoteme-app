package com.example.adoteme_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.domain.model.LoginRequest
import com.example.adoteme_app.data.network.api.AuthApiService
import kotlinx.coroutines.launch
import com.example.adoteme_app.domain.model.LoginResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class LoginState {
    data object Success : LoginState()
    data object Loading : LoginState()
    data object Require2FA : LoginState()
    data object Error : LoginState()
    data object Idle : LoginState()
}

class LoginViewModel(
    private val api: AuthApiService
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val _token = MutableStateFlow("")
    val token: StateFlow<String> = _token

    private val _userId = MutableStateFlow(0L)
    val userId: StateFlow<Long> = _userId

    private var _email = ""
    var email: String = _email

    private var _message = ""
    var message: String = _message


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = api.login(LoginRequest(email, password))

                when (response.code()) {
                    200 -> {
                        val body = response.body()?.string()
                        if (body != null) {
                            val loginResponse = Gson().fromJson(body, LoginResponse::class.java)
                            _token.value = loginResponse.token
                            _userId.value = loginResponse.idUser
                            _loginState.value = LoginState.Success
                        } else {
                            _loginState.value = LoginState.Error
                        }
                    }

                    202 -> {
                        _loginState.value = LoginState.Require2FA
                        Log.i("Login", "Login 2FA " )
                    }

                    else -> {
                        Log.e("Login", "Erro ao realizar o Login")
                        _loginState.value = LoginState.Error
                    }
                }

            } catch (e: Exception) {
                Log.e("Login", "Exeção ao realizar o login " + e.message)
                _loginState.value = LoginState.Error
            }
        }
    }

    fun validarOtp(email: String, otp: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = api.validarOtp(email, otp)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _token.value = body.token
                        _userId.value = body.idUser
                        _loginState.value = LoginState.Success
                    } else {
                        _loginState.value = LoginState.Error
                    }
                } else {
                    _loginState.value = LoginState.Error
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error
            }
        }
    }

    fun ativarTwoFactorAuth(email: String, success: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.ativarTwoFactorAuth(email = email)
                _message =  "2FA ativado com sucesso."
                success()
            } catch (e: Exception) {
                Log.e("2FA", "Erro ao abilitar 2fa: ${e.message}")
            }
        }
    }
}