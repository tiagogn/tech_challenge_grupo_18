package br.com.fiap.lanchonete.core.application.ports.output.repository

import br.com.fiap.lanchonete.core.domain.Pedido
import br.com.fiap.lanchonete.core.domain.StatusPedido
import java.util.Optional
import java.util.UUID

interface PedidoRepository {
    fun findByStatus(statusPedido: StatusPedido): List<Pedido>
    fun save(pedido: Pedido): Pedido
    fun findById(id: UUID): Optional<Pedido>
}