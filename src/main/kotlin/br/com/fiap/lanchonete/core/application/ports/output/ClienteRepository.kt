package br.com.fiap.lanchonete.core.application.ports.output

import br.com.fiap.lanchonete.core.domain.entities.Cliente
import java.util.*

interface ClienteRepository {
    fun save(cliente: Cliente): Cliente
    fun findByCPF(cpf: String): Optional<Cliente>
}