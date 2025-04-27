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

    fun cadastrarAdotante(adotante: AdotanteRequest, fotoFile: File?) {
        viewModelScope.launch {
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
            } catch (e: Exception) {
                Log.i("Cadastro", "Cadastro falhou " + e.message)
                _cadastroConcluido.value = false
            }
        }
    }
}