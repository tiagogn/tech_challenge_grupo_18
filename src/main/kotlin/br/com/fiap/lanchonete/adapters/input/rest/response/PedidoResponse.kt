package br.com.fiap.lanchonete.adapters.input.rest.response

import br.com.fiap.lanchonete.core.domain.entities.ItemPedido
import br.com.fiap.lanchonete.core.domain.entities.Pedido
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class PedidoResponse(
    var id: UUID?,
    val cliente: ClienteResponse?,
    val itens: List<ItemPedidoResponse>,
    val total: BigDecimal,
    val status: Pedido.StatusPedido,
    val criadoEm: LocalDateTime,
    val atualizadoEm: LocalDateTime
)

data class ItemPedidoResponse(
    var id: UUID?,
    val nomeProduto: String,
    val quantidade: Int,
    val precoUnitario: BigDecimal
)

fun Pedido.toResponse(): PedidoResponse {
    return PedidoResponse(
        id = this.id,
        cliente = this.cliente?.toResponse(),
        itens = this.itens.map { it.toResponse() },
        total = this.total,
        status = this.status,
        criadoEm = this.criadoEm,
        atualizadoEm = this.atualizadoEm
    )
}

fun ItemPedido.toResponse(): ItemPedidoResponse {
    return ItemPedidoResponse(
        id = this.id,
        nomeProduto = this.nomeProduto,
        quantidade = this.quantidade,
        precoUnitario = this.precoUnitario
    )
}