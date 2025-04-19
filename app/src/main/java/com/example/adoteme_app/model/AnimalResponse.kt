package com.example.adoteme_app.model

data class AnimalResponse(
    val id: Long,
    val nome: String,
    val idade: Int,
    val imagem: String,
    val imagem2: String?,
    val imagem3: String?,
    val imagem4: String?,
    val imagem5: String?,
    val distancia: Int,
    val porte: String,
    val especie: String,
    val sexo: String,
    val personalidade: PersonalidadeDto
)

data class PersonalidadeDto(
    val energia: Int,
    val sociabilidade: Int,
    val tolerante: Int,
    val obediente: Int,
    val territorial: Int,
    val inteligencia: Int,

)
