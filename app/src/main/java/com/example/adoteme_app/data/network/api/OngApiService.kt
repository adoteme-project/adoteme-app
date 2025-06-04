package com.example.adoteme_app.data.network.api

import com.example.adoteme_app.domain.model.OngDadosBancariosAnimalDto
import com.example.adoteme_app.domain.model.OngResponseAllDto
import retrofit2.Response
import retrofit2.http.GET

interface OngApiService {
    @GET("ongs/com-dados-bancarios")
    suspend fun getOngs(): Response<List<OngResponseAllDto>>

    @GET("ongs/listagem-ongs-com-animais-dados-bancarios")
    suspend fun getOngsComAnimais(): Response<List<OngDadosBancariosAnimalDto>>
}