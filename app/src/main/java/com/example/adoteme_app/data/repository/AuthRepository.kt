package com.example.adoteme_app.data.repository

interface AuthRepository {
    suspend fun enviarCodigo(email: String)
    suspend fun validarCodigo(email: String, codigo: String): Boolean
    suspend fun redefinirSenha(email: String, novaSenha: String)
}