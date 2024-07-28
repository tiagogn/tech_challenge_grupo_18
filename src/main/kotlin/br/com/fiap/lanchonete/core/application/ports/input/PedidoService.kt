package br.com.fiap.lanchonete.core.application.ports.input

import br.com.fiap.lanchonete.core.domain.entities.ItemPedido
import br.com.fiap.lanchonete.core.domain.entities.Pedido
import br.com.fiap.lanchonete.core.domain.entities.StatusPedido
import java.util.*

interface PedidoService {
    fun criarPedido(clienteId: UUID?, itens: List<ItemPedido>): Pedido
    fun atualizarStatusPedido(pedidoId: UUID, novoStatus: StatusPedido): Pedido
    fun listarPedidosPorStatus(statusPedido: StatusPedido): List<Pedido>
    fun buscarPorId(pedidoId: UUID): Pedido
    fun enviandoPedidoParaCozinha(pedidoId: UUID): Pedido
    fun pedidoPronto(pedidoId: UUID): Pedido
    fun pedidoFinalizado(pedidoId: UUID): Pedido
}