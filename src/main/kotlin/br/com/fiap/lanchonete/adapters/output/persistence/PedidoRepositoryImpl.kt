package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.application.ports.output.repository.PedidoRepository
import br.com.fiap.lanchonete.core.domain.entities.Pedido
import br.com.fiap.lanchonete.core.domain.entities.StatusPedido
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PedidoRepositoryImpl : PedidoRepository, JpaRepository<Pedido, UUID> {
    @Query("select p from Pedido p where p.status = :statusPedido")
    override fun findByStatus(statusPedido: StatusPedido): List<Pedido>
}