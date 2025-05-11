package com.example.adoteme_app.model

data class OngDadosBancariosAnimalDto(
    val id: Long,
    val nome: String,
    val email: String,
    val telefone: String,
    val cnpj: String,
    val banco: String,
    val agencia: String,
    val conta: String,
    val tipoConta: String,
    val chavePix: String,
    val nomeTitular: String,
    val qrCode: String,
    val endereco: EnderecoResponseOngDto,
    val animais: List<AnimalOngDto>
)

data class AnimalOngDto(
    val id: Long,
    val nome: String,
    val idade: Int,
    val imagem: String
)
