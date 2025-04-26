package com.example.adoteme_app.auth.presentation.register_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.interfaces.Adotante.AdotanteApiService
import com.example.adoteme_app.model.UserRegister.UserRegistrationDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.RequestBody.Companion.toRequestBody

class RegistrationViewModel(
    private val api : AdotanteApiService,
): ViewModel() {

    private val _userState = MutableStateFlow<UserRegistrationDTO?>(null)
    val userState: StateFlow<UserRegistrationDTO?> = _userState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun CadastrarAdotante(
        nome: String,
        email: String,
        celular: String,
        dtNasc: String,
        cep: String,
        estado: String,
        senha: String
    ){
        viewModelScope.launch {
            try{
                val response = api.AddAdotante(
                    nome.toRequestBodyFormData(),
                    email.toRequestBodyFormData(),
                    celular.toRequestBodyFormData(),
                    dtNasc.toRequestBodyFormData(),
                    cep.toRequestBodyFormData(),
                    estado.toRequestBodyFormData(),
                    senha.toRequestBodyFormData()
                )
                _userState.value = response
            }catch (e: Exception){
                _errorState.value = "Erro ao cadastrar adotante: ${e.message}"
            }
        }
    }

}