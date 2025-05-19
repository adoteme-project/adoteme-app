package com.example.adoteme_app.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.interfaces.AdotanteApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AdotanteViewModel(
    private val api: AdotanteApiService
) : ViewModel() {

    private val _cadastroConcluido = MutableStateFlow(false)
    val cadastroConcluido: StateFlow<Boolean> = _cadastroConcluido

    private val _cadastroErro = MutableStateFlow<String?>(null)
    val cadastroErro: StateFlow<String?> = _cadastroErro

    private val _aplicacaoData = MutableStateFlow<List<AdotanteListaRequisicaoDto>>(emptyList())
    val aplicacaoData: StateFlow<List<AdotanteListaRequisicaoDto>> = _aplicacaoData

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun cadastrarAdotante(adotante: AdotanteRequest, fotoFile: File?) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val gson = Gson()
                val adotanteJson = gson.toJson(adotante)

                val adotanteRequestBody = RequestBody.create(
                    "application/json; charset=utf-8".toMediaTypeOrNull(),
                    adotanteJson
                )

                val fotoPart = fotoFile?.let {
                    val requestFile = it.asRequestBody("image/*".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("fotoPerfil", it.name, requestFile)
                }

                val response = api.cadastrarAdotante(adotanteRequestBody, fotoPart)

                Log.i("Cadastro", "Cadastrado adotante com sucesso " + response.nome)

                _cadastroConcluido.value = true
                _cadastroErro.value = null
            } catch (e: Exception) {
                Log.i("Cadastro", "Cadastro falhou " + e.message)
                _cadastroConcluido.value = false
                _cadastroErro.value = e.message ?: "Erro desconhecido"
            } finally {
                _loading.value = false
            }
        }
    }

    fun carregarAplicacao(idAdotante: Long) {
        viewModelScope.launch {
            try {
                val response = api.obterRequisicaoAdotante(idAdotante)
                _aplicacaoData.value = response
            } catch (e: Exception) {
                Log.e("PerfilAplicacao", "Erro ao carregar dados", e)
            }
        }
    }
}