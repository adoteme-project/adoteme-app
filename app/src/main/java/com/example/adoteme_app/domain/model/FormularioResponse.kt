package com.example.adoteme_app.domain.model

data class FormularioResponse (
    val temCrianca: Boolean,
    val moradoresConcordam: Boolean,
    val temPet: Boolean,
    val seraResponsavel: Boolean,
    val moraEmCasa: Boolean,
    val isTelado: Boolean,
    val casaPortaoAlto: Boolean
)