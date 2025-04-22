package com.example.adoteme_app.interfaces

import com.example.adoteme_app.model.Ong
import retrofit2.http.GET

interface OngApiService {

    @GET("ongs/listagem-ongs-com-animais-dados-bancarios")
    suspend fun getOngs(): List<Ong>

}