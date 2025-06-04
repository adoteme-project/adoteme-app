package com.example.adoteme_app.domain.model

data class OngResponseAllDto(
    val id: Long,
    val nome: String,
    val email: String,
    val telefone: String,
    val cnpj: String,
    val endereco: EnderecoResponseOngDto,
    val dadosBancarios: DadosBancariosDto?,
    val imagem: String
)

data class EnderecoResponseOngDto(
    val id: Long,
    val cep: String,
    val rua: String,
    val bairro: String,
    val cidade: String,
    val estado: String
)

data class DadosBancariosDto(
    val id: Long,
    val banco: String,
    val agencia: String,
    val conta: String,
    val tipoConta: String,
    val chavePix: String,
    val nomeTitular: String,
    val qrCode: String
)
