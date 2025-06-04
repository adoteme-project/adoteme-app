package com.example.adoteme_app.data.network.api

import com.example.adoteme_app.domain.model.RequisicaoCreateDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RequisicaoApiService {
    @POST("requisicoes")
    suspend fun criarRequisicao(@Body dto: RequisicaoCreateDto): Response<Void>

}
