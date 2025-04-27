package com.example.adoteme_app.interfaces

import com.example.adoteme_app.model.OngResponseAllDto
import retrofit2.Response
import retrofit2.http.GET

interface OngApiService {
    @GET("/ongs/com-dados-bancarios")
    suspend fun getOngs(): Response<List<OngResponseAllDto>>
}