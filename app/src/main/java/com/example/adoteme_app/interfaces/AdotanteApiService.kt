package com.example.adoteme_app.interfaces

import com.example.adoteme_app.model.AdotanteRequest
import com.example.adoteme_app.model.AdotanteResponse
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File

interface AdotanteApiService {

    @Multipart
    @POST("adotantes/cadastrar")
    suspend fun cadastrarAdotante(
        @Part("adotante") adotante: AdotanteRequest,
        @Part fotoPerfil: File?
    ): AdotanteResponse

}