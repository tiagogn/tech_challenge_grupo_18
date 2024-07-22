package br.com.fiap.lanchonete.core.application.ports.input

import br.com.fiap.lanchonete.core.domain.entities.ItemPedido
import br.com.fiap.lanchonete.core.domain.entities.Pedido
import java.util.*

interface PedidoService {
    fun criarPedido(clienteId: UUID?, itens: List<ItemPedido>): Pedido
    fun atualizarStatusPedido(pedidoId: UUID, novoStatus: Pedido.StatusPedido): Pedido
    fun listarPedidosNaFila(): List<Pedido>
}