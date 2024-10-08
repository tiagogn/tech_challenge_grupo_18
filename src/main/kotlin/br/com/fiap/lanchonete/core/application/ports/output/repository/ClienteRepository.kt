package br.com.fiap.lanchonete.core.application.ports.output.repository

import br.com.fiap.lanchonete.core.domain.Cliente
import java.util.*

interface ClienteRepository {
    fun save(cliente: Cliente): Cliente
    fun findByCPF(cpf: String): Optional<Cliente>
    fun findById(id: UUID?): Optional<Cliente>
}