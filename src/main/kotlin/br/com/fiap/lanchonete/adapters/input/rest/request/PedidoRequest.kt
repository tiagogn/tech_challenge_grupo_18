package br.com.fiap.lanchonete.adapters.input.rest.request

import br.com.fiap.lanchonete.core.domain.entities.ItemPedido
import br.com.fiap.lanchonete.core.domain.entities.Pedido
import br.com.fiap.lanchonete.core.domain.entities.Produto
import java.math.BigDecimal
import java.util.*

data class PedidoRequest(
    val clienteId: UUID?,
    val itens: List<ItemPedidoRequest>
)

data class ItemPedidoRequest(
    val produtoId: UUID,
    val nomeProduto: String,
    val quantidade: Int,
    val precoUnitario: BigDecimal,
    val categoria: String
)

fun ItemPedidoRequest.toModel(): ItemPedido {
    return ItemPedido(
        id = null,
        produto = Produto(
            id = this.produtoId,
            nome = this.nomeProduto,
            preco = this.precoUnitario,
            categoria = Produto.Categoria.valueOf(this.categoria)
        ),
        nomeProduto = this.nomeProduto,
        quantidade = this.quantidade,
        precoUnitario = this.precoUnitario,
        categoria = this.categoria
    )
}

data class AtualizarStatusRequest(
    val novoStatus: Pedido.StatusPedido
)

data class historicoPedido(
    val idPedido: Pedido
)