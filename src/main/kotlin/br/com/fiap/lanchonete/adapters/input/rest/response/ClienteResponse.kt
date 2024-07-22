package br.com.fiap.lanchonete.adapters.input.rest.response

import br.com.fiap.lanchonete.core.domain.entities.Cliente

data class ClienteResponse(
    val identificador: String,
    val nome: String,
    val email: String,
    val cpf: String
)

fun Cliente.toResponse(): ClienteResponse {
    return ClienteResponse(
        identificador = this.identificador!!.toString(),
        nome = this.nome,
        email = this.email,
        cpf = this.cpf
    )
}