package br.com.fiap.lanchonete.core.domain

import java.util.UUID

data class Cliente(

    var id: UUID? = null,

    val nome: String,

    val email: String,

    val cpf: String
)