package com.example.adoteme_app.model.UserRegister

data class UserRegistrationDTO(
    val Nome: String,
    val Email: String,
    val Celular: String,
    val DataNascimento: String,
    val Cep: String,
    val Estado: String,
    val Senha: String,
)