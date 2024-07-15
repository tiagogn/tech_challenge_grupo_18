package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.application.ports.output.ClienteRepository
import br.com.fiap.lanchonete.core.domain.entities.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClienteRepositoryImpl : ClienteRepository, JpaRepository<Cliente, UUID> {

    @Query("SELECT c FROM Cliente c WHERE c.cpf = :cpf")
    override fun findByCPF(cpf: String): Optional<Cliente>
}