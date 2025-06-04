package com.example.adoteme_app.domain.model

data class AdotanteResponse (
    val nome: String,
    val email: String,
    val senha: String,
    val dtNasc: String,
    val celular: String,
    val cep: String,
    val numero: String,
    val formulario: Formulario,
    val fotoPerfil: String,
)