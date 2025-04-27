package com.example.adoteme_app.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.interfaces.AdotanteApiService
import kotlinx.coroutines.launch
import java.io.File

class AdotanteViewModel(
    private val api: AdotanteApiService
) : ViewModel() {

    fun cadastrarAdotante(adotante: AdotanteRequest, fotoFile: File?) {
        viewModelScope.launch {
            try {
                val response = api.cadastrarAdotante(adotante, fotoFile)

                Log.i("Cadastro", "Cadastrado adotante com sucesso " + response.nome)
            } catch (e: Exception) {
                Log.i("Cadastro", "Cadastro falhou " + e.message)
            }
        }
    }
}