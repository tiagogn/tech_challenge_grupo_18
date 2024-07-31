package br.com.fiap.lanchonete.core.application.ports.output.repository

import br.com.fiap.lanchonete.core.domain.entities.Pedido
import br.com.fiap.lanchonete.core.domain.entities.StatusPedido
import java.util.*

interface PedidoRepository {
    fun findByStatus(statusPedido: StatusPedido): List<Pedido>
    fun save(pedido: Pedido): Pedido
    fun findById(pedidoId: UUID): Optional<Pedido>
}