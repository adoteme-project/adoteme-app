package com.example.adoteme_app.perfil.data.use_case

import com.example.adoteme_app.data.repository.PerfilRepository
import com.example.adoteme_app.model.AdotanteDados
import com.example.adoteme_app.model.AdotantePutRequest

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

    suspend fun atualizarAdotante(id: Long, request: AdotantePutRequest): AdotanteDados {
        return perfilRepository.atualizarAdotante(id, request)
    }

    fun salvarAdotante(adotante: AdotanteDados) {
        perfilRepository.salvarAdotante(adotante)
    }
}