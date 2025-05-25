package com.example.adoteme_app.data.repository

import android.content.Context
import com.example.adoteme_app.interfaces.AdotanteApiService
import com.example.adoteme_app.model.AdotanteDados
import com.example.adoteme_app.model.AdotantePutRequest
import com.example.adoteme_app.model.Endereco
import com.example.adoteme_app.model.Formulario
import com.example.adoteme_app.model.FormularioResponse
import com.google.gson.Gson

class PerfilRepository(
    private val context: Context,
    private val adotanteApi: AdotanteApiService
) {
    private val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun salvarAdotante(adotante: AdotanteDados) {
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(adotante)
        editor.putString("adotante", json)
        editor.apply()
    }

    fun salvarToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun buscarAdotanteSalvo(): AdotanteDados? {
        val json = sharedPreferences.getString("adotante", null)
        return if (json != null) {
            Gson().fromJson(json, AdotanteDados::class.java)
        } else {
            null
        }
    }

    fun buscarTokenSalvo(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun limparDados() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    suspend fun atualizarAdotante(id: Long, request: AdotantePutRequest): AdotanteDados {
        return adotanteApi.atualizarDadosAdotante(id = id, adotantePutRequest = request)
    }

    suspend fun atualizarAdotanteFormulario(id: Long, request: Formulario): AdotanteDados {
        return adotanteApi.atualizarFormularioAdotante(id, request)
    }

    suspend fun buscarFormularioAdotante(id: Long): FormularioResponse {
        return adotanteApi.getFormularioAdotante(id)
    }
}