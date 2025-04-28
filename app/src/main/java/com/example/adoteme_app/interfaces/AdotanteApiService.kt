package com.example.adoteme_app.interfaces

import com.example.adoteme_app.model.AdotanteListaRequisicaoDto
import com.example.adoteme_app.model.AdotanteDados
import com.example.adoteme_app.model.AdotanteRequest
import com.example.adoteme_app.model.AdotanteResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import java.io.File

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
}