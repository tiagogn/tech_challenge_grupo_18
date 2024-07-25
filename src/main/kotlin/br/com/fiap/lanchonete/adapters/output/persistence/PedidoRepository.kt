package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.domain.entities.Pedido
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PedidoRepository : JpaRepository<Pedido, UUID> {

    fun findByStatusNotOrderByCriadoEmAsc(status: Pedido.StatusPedido): List<Pedido>

    @Query("SELECT * FROM pedido c WHERE c.id = :id", nativeQuery = true)
    fun findByIdPedido(id: UUID): List<Pedido>
}