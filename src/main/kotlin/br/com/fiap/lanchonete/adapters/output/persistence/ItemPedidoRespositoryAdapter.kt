package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.domain.CategoriaProduto
import br.com.fiap.lanchonete.core.domain.ItemPedido
import br.com.fiap.lanchonete.core.domain.Produto
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ItemPedidoRespositoryAdapter(
    private val jdbcClient: JdbcClient
) {
    fun persist(itemPedido: ItemPedido, pedidoId: UUID) {
        val id = UUID.randomUUID()
        jdbcClient.sql(
            """
            INSERT INTO item_pedido (id, pedido_id, produto_id, nome_produto, quantidade, preco_unitario, categoria) 
            VALUES (:id, :pedido_id, :produto_id, :nome_produto, :quantidade, :preco_unitario, :categoria)
        """
        )
            .params(
                mapOf(
                    "id" to id,
                    "pedido_id" to pedidoId,
                    "produto_id" to itemPedido.produto.id,
                    "nome_produto" to itemPedido.nomeProduto,
                    "quantidade" to itemPedido.quantidade,
                    "preco_unitario" to itemPedido.precoUnitario,
                    "categoria" to itemPedido.categoria
                )
            )
            .update()

        itemPedido.id = id
    }

    fun findByPedidoId(pedidoId: UUID): List<ItemPedido> {
        return jdbcClient.sql(
            """
            SELECT * FROM item_pedido WHERE pedido_id = :pedido_id
        """
        )
            .params(
                mapOf(
                    "pedido_id" to pedidoId
                )
            )
            .query { rs, _ ->
                ItemPedido(
                    id = UUID.fromString(rs.getString("id")),
                    produto = findProdutoById(UUID.fromString(rs.getString("produto_id"))),
                    nomeProduto = rs.getString("nome_produto"),
                    quantidade = rs.getInt("quantidade"),
                    precoUnitario = rs.getBigDecimal("preco_unitario"),
                    categoria = rs.getString("categoria")
                )
            }.list()
    }

    private fun findProdutoById(id: UUID): Produto {
        return jdbcClient.sql(
            """
            SELECT * FROM produto WHERE id = :id
        """
        )
            .params(
                mapOf(
                    "id" to id
                )
            )
            .query { rs, _ ->
                Produto(
                    id = UUID.fromString(rs.getString("id")),
                    nome = rs.getString("nome"),
                    preco = rs.getBigDecimal("preco"),
                    categoria = CategoriaProduto.valueOf(rs.getString("categoria"))
                )
            }.optional().orElse(null)
    }
}