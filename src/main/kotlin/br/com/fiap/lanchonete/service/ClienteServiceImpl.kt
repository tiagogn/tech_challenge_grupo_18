package br.com.fiap.lanchonete.service

import br.com.fiap.lanchonete.domain.Cliente
import br.com.fiap.lanchonete.exceptions.ResourceNotFoundException
import br.com.fiap.lanchonete.repository.ClienteRepository
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