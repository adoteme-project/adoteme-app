package com.example.adoteme_app.interfaces

import com.example.adoteme_app.model.RequisicaoCreateDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RequisicaoApiService {
    @POST("requisicoes")
    suspend fun criarRequisicao(@Body dto: RequisicaoCreateDto): Response<Void>
}
