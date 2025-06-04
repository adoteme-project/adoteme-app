package com.example.adoteme_app.domain.model

sealed class StatusRequisicao {
    object Sucesso : StatusRequisicao()
    object Carregando : StatusRequisicao()
    data class Erro(val mensagem: String) : StatusRequisicao()
    object Nada : StatusRequisicao()
    object RequisicaoDuplicada: StatusRequisicao()
}