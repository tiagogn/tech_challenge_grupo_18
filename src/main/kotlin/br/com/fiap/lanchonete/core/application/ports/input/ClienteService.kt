package br.com.fiap.lanchonete.core.application.ports.input

import br.com.fiap.lanchonete.core.domain.entities.Cliente

interface ClienteService {
    fun cadastrarCliente(cliente: Cliente): Cliente
    fun buscarClientePorCPF(cpf: String): Cliente
}