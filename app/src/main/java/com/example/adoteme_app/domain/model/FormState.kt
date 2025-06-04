package com.example.adoteme_app.domain.model

import android.util.Patterns

data class UserFormState (
    var nome: String = "",
    var email: String = "",
    var celular: String = "",
    var dataNascimento: String = "",
    var cep: String = "",
    var estado: String = "",
    var cidade: String = "",
    var numero: String = "",
    var senha: String = "",
    var confirmarSenha: String = ""
)

data class ProfileFormState (
    var nome: String = "",
    var email: String = "",
    var celular: String = "",
    var dataNascimento: String = "",
    var cep: String = "",
    var estado: String = "",
    var cidade: String = "",
    var numero: String = "",
    var senha: String = "",
)


data class UserFormErros(
    var nome: String? = null,
    var email: String? = null,
    var celular: String? = null,
    var cep: String? = null,
    var senha: String? = null,
    var confirmarSenha: String? = null
)

object FormValidator {
    fun validateNome(nome: String) = if (nome.isBlank() || nome.length <= 2) "Nome é obrigatório" else null
    fun validateEmail(email: String) = if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) "Email inválido" else null
    fun validateSenha(senha: String) = if (senha.length < 8) "Senha deve ter no mínimo 8 caracteres" else null
    fun validateConfirmarSenha(senha: String, confirmar: String) = if (senha != confirmar) "As senhas não coincidem" else null
    fun validateCelular(celular: String): String? {
        val regex = Regex("^\\+?[1-9]\\d{1,14}$")
        return if (!regex.matches(celular)) "Número de celular inválido (ex: +5511999999999)" else null
    }
    fun validateCEP(cep: String) = if (cep.length != 8) "CEP deve ter 8 dígitos" else null
}