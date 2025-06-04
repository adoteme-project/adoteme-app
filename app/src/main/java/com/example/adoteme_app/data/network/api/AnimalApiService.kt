package com.example.adoteme_app.data.network.api

import com.example.adoteme_app.domain.model.AnimalFavoritoUsuarioDto
import com.example.adoteme_app.domain.model.AnimalResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.Response


interface AnimalApiService {

    @GET("animais/todos-animais-com-personalidade/")
    suspend fun getTodosAnimais(): List<AnimalResponse>

    @GET("animais/animal-com-personalidade/{idAnimal}")
    suspend fun getAnimalById(@Path("idAnimal") id: Long): AnimalResponse

    @GET("adotantes/animais-favoritos-usuario/{id}")
    suspend fun getFavoritosByAdotanteId(@Path("id") id: Long): AnimalFavoritoUsuarioDto

    @POST("adotantes/favoritar-animal/{idAdotante}/{idAnimal}")
    suspend fun favoritarAnimal(
        @Path("idAdotante") idAdotante: Long,
        @Path("idAnimal") idAnimal: Long
    ): Response<Unit>

    @DELETE("adotantes/desfavoritar-animal/{idAdotante}/{idAnimal}")
    suspend fun desfavoritarAnimal(
        @Path("idAdotante") idAdotante: Long,
        @Path("idAnimal") idAnimal: Long
    ): Response<Unit>


}
