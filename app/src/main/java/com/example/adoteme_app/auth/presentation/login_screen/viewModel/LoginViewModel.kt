package com.example.adoteme_app.auth.presentation.login_screen.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.auth.presentation.login_screen.auth_service.AuthApiService
import com.example.adoteme_app.auth.presentation.login_screen.auth_service.Request.LoginRequest
import kotlinx.coroutines.launch

class LoginViewModel(
    private val api : AuthApiService
) : ViewModel(){

    var token = mutableStateOf("");
    var userId = mutableStateOf(0);

    fun login(email: String, password: String){
        viewModelScope.launch {
            try{
                val response = api.login(LoginRequest(email,password))

                token.value = response.token
                userId.value = response.idUser
                Log.i("Retorno login","dados token:${token.value} idUser: ${userId.value}")
            }catch (e: Exception){
                Log.e("Login", "Erro ao tentar autenticar",e)
            }
        }

    }



}