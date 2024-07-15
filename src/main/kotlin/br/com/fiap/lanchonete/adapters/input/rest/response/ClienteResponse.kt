package br.com.fiap.lanchonete.adapters.input.rest.response

data class ClienteResponse(
    val identificador: String,
    val nome: String,
    val email: String,
    val cpf: String
)
