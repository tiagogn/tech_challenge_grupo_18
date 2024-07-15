package br.com.fiap.lanchonete.adapters.input.rest.request

import br.com.fiap.lanchonete.core.domain.entities.Cliente
import java.util.*

data class ClienteRequest(
    var identificador: UUID? = null,
    val nome: String,
    val email: String,
    val cpf: String
){
    fun toModel(): Cliente {
        return Cliente(
            identificador = this.identificador,
            nome = this.nome,
            email = this.email,
            cpf = this.cpf
        )
    }
}