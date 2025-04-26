package com.example.adoteme_app.interfaces.Adotante

import com.example.adoteme_app.model.UserRegister.UserRegistrationDTO
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface AdotanteApiService {

    @Multipart
    @POST("/adotantes/cadastrar")
    suspend fun AddAdotante(
        @Part("nome") nome: RequestBody,
        @Part("email") email: RequestBody,
        @Part("celular") celular: RequestBody,
        @Part("dtNasc") dtNasc: RequestBody,
        @Part("cep") cep: RequestBody,
        @Part("estado") estado: RequestBody,
        @Part("senha") senha: RequestBody,
    ): UserRegistrationDTO

    @POST("/adotantes/preencher-formulario/{idAdotante}")
    suspend fun PreencherFormularioAdotante(
        @Path("idAdotante") idAdotante: Long)
    : UserRegistrationDTO
}