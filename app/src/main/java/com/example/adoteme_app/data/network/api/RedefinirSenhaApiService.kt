package com.example.adoteme_app.data.network.api

import com.example.adoteme_app.domain.model.ResetarSenhaRequest
import com.example.adoteme_app.domain.model.ValidacaoCodigoRequest
import com.example.adoteme_app.presentation.viewmodel.EmailRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface RedefinirSenhaApiService {

    @POST("redefinicao-senha/request-code")
    suspend fun enviarCodigo(@Body email: EmailRequest)

    @POST("redefinicao-senha/validar-codigo")
    suspend fun validarCodigo(@Body validacaoCodigoRequest: ValidacaoCodigoRequest): Boolean

    @PUT("redefinicao-senha/resetar-senha")
    suspend fun redefinirSenha(@Body resetarSenhaRequest: ResetarSenhaRequest)
}