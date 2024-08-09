package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.application.ports.output.repository.PedidoRepository
import br.com.fiap.lanchonete.core.domain.entities.Pedido
import br.com.fiap.lanchonete.core.domain.entities.StatusPedido
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Qualifier("pedidoRepository")
class PedidoRepositoryAdadapter : PedidoRepository {
    override fun save(pedido: Pedido): Pedido {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID): Optional<Pedido> {
        TODO("Not yet implemented")
    }

    override fun findByStatus(statusPedido: StatusPedido): List<Pedido> {
        TODO("Not yet implemented")
    }
}