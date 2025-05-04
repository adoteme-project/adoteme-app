package com.example.adoteme_app.interfaces

import com.example.adoteme_app.model.AdotanteListaRequisicaoDto
import com.example.adoteme_app.model.RequisicaoCreateDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RequisicaoApiService {
    @POST("requisicoes")
    suspend fun criarRequisicao(@Body dto: RequisicaoCreateDto): Response<Void>

}
