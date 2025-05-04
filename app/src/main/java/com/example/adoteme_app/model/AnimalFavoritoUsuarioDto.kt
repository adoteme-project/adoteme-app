package com.example.adoteme_app.model

data class AnimalFavoritoUsuarioDto(
    val usuarioId: Long,
    val animaisfavoritos: List<AnimalFavoritoDto>
)

data class AnimalFavoritoDto(
    val animalId: Long,
    val nome: String,
    val idade: Int,
    val especie: String,
    val sexo: String,
    val porte: String,
    val distancia: Int,
    val imagem: String
)
