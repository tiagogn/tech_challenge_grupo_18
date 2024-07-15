package br.com.fiap.lanchonete.core.application.services

import br.com.fiap.lanchonete.core.application.ports.inputs.ClienteService
import br.com.fiap.lanchonete.core.domain.entities.Cliente
import br.com.fiap.lanchonete.core.application.services.exceptions.ResourceNotFoundException
import br.com.fiap.lanchonete.core.domain.repository.ClienteRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
@Qualifier("clienteService")
class ClienteServiceImpl(
    private val clienteRepository: ClienteRepository
): ClienteService {
    override fun cadastrarCliente(cliente: Cliente): Cliente {
        return clienteRepository.save(cliente)
    }

    override fun buscarClientePorCPF(cpf: String): Cliente {
        return clienteRepository.findByCPF(cpf)
            .orElseThrow { ResourceNotFoundException("Cliente não encontrado") }
    }
}