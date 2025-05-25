package com.example.adoteme_app.model

import android.util.Log
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.auth.data.ProfileFormState
import com.example.adoteme_app.perfil.data.use_case.PerfilUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(
    private val perfilUseCase: PerfilUseCase
) : ViewModel() {

    private val _adotanteDados = MutableStateFlow<AdotanteDados?>(null)
    val adotanteDados: StateFlow<AdotanteDados?> = _adotanteDados

    private val _adotanteFormulario = MutableStateFlow<FormularioResponse?>(null)
    val adotanteFormulario: StateFlow<FormularioResponse?> = _adotanteFormulario

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

    fun buscarFormularioUsuario(id: Long) {
        viewModelScope.launch {
            try {
                val response = perfilUseCase.buscarFormularioAdotanteCase(id)

                _adotanteFormulario.value = response
            } catch (e: Exception) {
                Log.e("PerfilViewModel", "Erro ao buscar o formulário")
            }
        }
    }

    fun logout(context: Context) {
        viewModelScope.launch {
            perfilUseCase.limparDadosUsuario()
            _adotanteDados.value = null
            _token.value = null

            context.getSharedPreferences("auth", Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply()
        }
    }

    fun atualizarAdotante(id: Long, form: ProfileFormState) {
        viewModelScope.launch {
            try {
                val request = AdotantePutRequest(
                    nome = form.nome,
                    email = form.email,
                    celular = form.celular,
                    dtNasc = form.dataNascimento,
                    numero = form.numero
                )

                val response = perfilUseCase.atualizarAdotante(id, request)

                perfilUseCase.salvarAdotante(response)
                _adotanteDados.value = response
            } catch (e: Exception) {
                Log.e("PerfilViewModel", "Erro ao atualizar dados", e)
            }
        }
    }

    fun atualizarAdotanteFormulario(id: Long, formularioResponse: FormularioResponse) {
        viewModelScope.launch {
            try {
                val response = perfilUseCase.atualizarAdotanteFormulario(id, formularioResponse)

                _adotanteFormulario.value = response
            } catch (e: Exception) {
                Log.e("PerfilViewModel", "Erro ao atualizar dados", e)
            }
        }
    }
}