package com.example.adoteme_app.interfaces

import com.example.adoteme_app.model.AnimalResponse
import retrofit2.http.GET

interface AnimalApiService {
    @GET("/animais/todos-animais-com-personalidade")
    suspend fun getTodosAnimais(): List<AnimalResponse>
}
