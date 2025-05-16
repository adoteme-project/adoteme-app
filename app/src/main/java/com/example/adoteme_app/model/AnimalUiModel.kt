package com.example.adoteme_app.model

data class AnimalUiModel(
    val id: Long,
    val nome: String,
    val idade: Int,
    val imagem: String,
    val especie: String = "",
    val sexo: String = "",
    val porte: String = "",
    val isFavoritado: Boolean
)

data class AnimalOngUiDto(
    val id: Long,
    val nome: String,
    val idade: Int,
    val imagem: String,
    val isFavoritado: Boolean
)

data class AnimalUiResponse(
    val id: Long,
    val nome: String,
    val idade: Int,
    val imagem: String,
    val imagem2: String?,
    val imagem3: String?,
    val imagem4: String?,
    val imagem5: String?,
    val distancia: Int?,
    val porte: String,
    val especie: String,
    val sexo: String,
    val personalidade: PersonalidadeDto,
    val isFavoritado: Boolean
)

data class AnimalUiFavoritoDto(
    val animalId: Long,
    val nome: String,
    val idade: Int,
    val especie: String,
    val sexo: String,
    val porte: String,
    val distancia: Int,
    val imagem: String,
    val isFavoritado: Boolean
)
