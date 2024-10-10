package br.com.fiap.lanchonete.core.application.ports.input

import br.com.fiap.lanchonete.core.domain.ItemPedido
import br.com.fiap.lanchonete.core.domain.Pedido
import br.com.fiap.lanchonete.core.domain.StatusPedido
import br.com.fiap.lanchonete.core.dto.PedidoStatusOutput
import java.util.*

interface PedidoService {
    fun criarPedido(clienteId: UUID?, itens: List<ItemPedido>): Pedido
    fun atualizarStatusPedido(pedidoId: UUID, novoStatus: StatusPedido): Pedido
    fun listarPedidosAgrupadosPorStatus(): List<PedidoStatusOutput>
    fun buscarPorId(pedidoId: UUID): Pedido
    fun enviandoPedidoParaCozinha(pedidoId: UUID): Pedido
    fun pedidoPronto(pedidoId: UUID): Pedido
    fun pedidoFinalizado(pedidoId: UUID): Pedido
}