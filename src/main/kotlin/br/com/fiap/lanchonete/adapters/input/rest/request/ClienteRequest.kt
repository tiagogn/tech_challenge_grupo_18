package br.com.fiap.lanchonete.adapters.input.rest.request

import br.com.fiap.lanchonete.core.domain.Cliente
import java.util.UUID

data class ClienteRequest(
    var id: UUID? = null,
    val nome: String,
    val email: String,
    val cpf: String
) {
    fun toModel(): Cliente {
        return Cliente(
            id = this.id,
            nome = this.nome,
            email = this.email,
            cpf = this.cpf
        )
    }
}