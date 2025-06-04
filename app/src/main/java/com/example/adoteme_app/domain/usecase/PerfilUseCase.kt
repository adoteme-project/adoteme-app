package com.example.adoteme_app.domain.usecase

import com.example.adoteme_app.data.repository.PerfilRepository
import com.example.adoteme_app.domain.model.AdotanteDados
import com.example.adoteme_app.domain.model.AdotantePutRequest
import com.example.adoteme_app.domain.model.FormularioResponse

class PerfilUseCase(
    private val perfilRepository: PerfilRepository
) {
    suspend fun buscarAdotanteSalvo(): AdotanteDados? {
        return perfilRepository.buscarAdotanteSalvo()
    }

    suspend fun buscarTokenSalvo(): String? {
        return perfilRepository.buscarTokenSalvo()
    }

    suspend fun limparDadosUsuario() {
        perfilRepository.limparDados()
    }


    fun salvarAdotante(adotante: AdotanteDados) {
        perfilRepository.salvarAdotante(adotante)
    }

}