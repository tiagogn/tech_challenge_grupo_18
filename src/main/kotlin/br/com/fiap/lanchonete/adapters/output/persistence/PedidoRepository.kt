package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.domain.entities.Pedido
import br.com.fiap.lanchonete.core.domain.entities.StatusPedido
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PedidoRepository : JpaRepository<Pedido, UUID> {
    @Query("select p from Pedido p where p.status = :statusPedido")
    fun findByStatus(statusPedido: StatusPedido): List<Pedido>
}