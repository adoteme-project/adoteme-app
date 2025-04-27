package com.example.adoteme_app.interfaces

import com.example.adoteme_app.model.AnimalFavoritoUsuarioDto
import com.example.adoteme_app.model.AnimalResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimalApiService {

    @GET("animais/todos-animais-com-personalidade/")
    suspend fun getTodosAnimais(): List<AnimalResponse>

    @GET("animais/animal-com-personalidade/{idAnimal}")
    suspend fun getAnimalById(@Path("idAnimal") id: Long): AnimalResponse


    @GET("adotantes/animais-favoritos-usuario/{id}")
    suspend fun getFavoritosByAdotanteId(@Path("id") id: Long): AnimalFavoritoUsuarioDto

}
