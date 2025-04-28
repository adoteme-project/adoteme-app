package com.example.adoteme_app.perfil.data.use_case

import com.example.adoteme_app.data.repository.PerfilRepository
import com.example.adoteme_app.model.AdotanteDados

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
}