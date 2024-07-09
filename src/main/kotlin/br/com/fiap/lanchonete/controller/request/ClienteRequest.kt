package br.com.fiap.lanchonete.controller.request

import br.com.fiap.lanchonete.domain.Cliente
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