package com.example.adoteme_app.data.network.api

import com.example.adoteme_app.domain.model.AdotanteListaRequisicaoDto
import com.example.adoteme_app.domain.model.AdotanteDados
import com.example.adoteme_app.domain.model.AdotantePutRequest
import com.example.adoteme_app.domain.model.AdotanteResponse
import com.example.adoteme_app.domain.model.FormularioResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface AdotanteApiService {

    @Multipart
    @POST("adotantes/cadastrar")
    suspend fun cadastrarAdotante(
        @Part("adotante") adotante: RequestBody,
        @Part fotoPerfil: MultipartBody.Part?
    ): AdotanteResponse

    @GET("adotantes/dados-foto-adotante/{id}")
    suspend fun getDadosAdotante(@Path("id") id: Long): AdotanteDados

    @GET("adotantes/lista-requisicao-adotante/{idAdotante}")
    suspend fun obterRequisicaoAdotante(@Path("idAdotante") idAdotante: Long): List<AdotanteListaRequisicaoDto>

    @PUT("adotantes/{id}")
    suspend fun atualizarDadosAdotante(
        @Path("id") id: Long,
        @Body adotantePutRequest: AdotantePutRequest
    ): AdotanteDados

    @PUT("adotantes/atualizacao-formulario/{id}/")
    suspend fun atualizarFormularioAdotante(
        @Path("id") id: Long,
        @Body adotanteForm: FormularioResponse
    ): FormularioResponse

    @GET("adotantes/formulario-adotante/{id}")
    suspend fun getFormularioAdotante(@Path("id") id: Long): FormularioResponse
}