package br.com.fiap.lanchonete.service

import br.com.fiap.lanchonete.domain.Cliente

interface ClienteService {
    fun cadastrarCliente(cliente: Cliente): Cliente
    fun buscarClientePorCPF(cpf: String): Cliente
}