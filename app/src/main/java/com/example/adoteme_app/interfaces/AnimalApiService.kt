package com.example.adoteme_app.interfaces

import com.example.adoteme_app.model.AnimalResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimalApiService {

    @GET("animais/todos-animais-com-personalidade/")
    suspend fun getTodosAnimais(): List<AnimalResponse>

    @GET("animal-com-personalidade/{idAnimal}")
    suspend fun getAnimalById(@Path("idAnimal") id: Int): AnimalResponse
}
