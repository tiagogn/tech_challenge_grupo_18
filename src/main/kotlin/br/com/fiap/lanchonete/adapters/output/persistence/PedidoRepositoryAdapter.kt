package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.application.ports.output.repository.PedidoRepository
import br.com.fiap.lanchonete.core.domain.entities.CategoriaProduto
import br.com.fiap.lanchonete.core.domain.entities.Cliente
import br.com.fiap.lanchonete.core.domain.entities.ItemPedido
import br.com.fiap.lanchonete.core.domain.entities.Pedido
import br.com.fiap.lanchonete.core.domain.entities.Produto
import br.com.fiap.lanchonete.core.domain.entities.StatusPedido
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
@Qualifier("pedidoRepository")
class PedidoRepositoryAdapter(
    private val jdbcClient: JdbcClient
) : PedidoRepository {
    override fun save(pedido: Pedido): Pedido {
        return if (pedido.id == null) {
            pedido.id = UUID.randomUUID()
            insert(pedido)
        } else {
            update(pedido)
        }
    }

    override fun findById(id: UUID): Optional<Pedido> {
        return jdbcClient.sql(
            """
            SELECT * FROM pedido WHERE id = :id
        """
        )
            .params(
                mapOf(
                    "id" to id
                )
            )
            .query { rs, _ -> mapPedido(rs) }
            .optional()
    }

    override fun findByStatus(statusPedido: StatusPedido): List<Pedido> {
        return jdbcClient.sql(
            """
            SELECT * FROM pedido WHERE status = :status
        """
        )
            .params(
                mapOf(
                    "status" to statusPedido.name
                )
            )
            .query { rs, _ -> mapPedido(rs) }
            .list()
    }

    private fun insert(pedido: Pedido): Pedido {
        jdbcClient.sql(
            """
            INSERT INTO pedido (id, cliente_id, total, status, criado_em, atualizado_em, pronto_em, finalizado_em) 
            VALUES (:id, :cliente_id, :total, :status, :criado_em, :atualizado_em, :pronto_em, :finalizado_em)
        """
        )
            .params(mapParams(pedido))
            .update()
        return pedido
    }

    private fun update(pedido: Pedido): Pedido {
        jdbcClient.sql(
            """
            UPDATE pedido 
            SET cliente_id = :cliente_id, total = :total, status = :status, 
                criado_em = :criado_em, atualizado_em = :atualizado_em, 
                pronto_em = :pronto_em, finalizado_em = :finalizado_em
            WHERE id = :id
        """
        )
            .params(mapParams(pedido))
            .update()
        return pedido
    }

    private fun mapParams(pedido: Pedido): Map<String, Any?> {
        return mapOf(
            "id" to pedido.id,
            "cliente_id" to pedido.cliente?.id,
            "total" to pedido.total,
            "status" to pedido.status.name,
            "criado_em" to pedido.criadoEm,
            "atualizado_em" to pedido.atualizadoEm,
            "pronto_em" to pedido.prontoEm,
            "finalizado_em" to pedido.finalizadoEm
        )
    }

    private fun mapPedido(rs: java.sql.ResultSet): Pedido {
        val pedidoId = UUID.fromString(rs.getString("id"))
        return Pedido(
            id = pedidoId,
            cliente = findClienteById(UUID.fromString(rs.getString("cliente_id"))),
            itens = findItensByPedidoId(pedidoId),
            total = rs.getBigDecimal("total"),
            status = StatusPedido.valueOf(rs.getString("status")),
            criadoEm = rs.getTimestamp("criado_em").toLocalDateTime(),
            atualizadoEm = rs.getTimestamp("atualizado_em").toLocalDateTime(),
            prontoEm = rs.getTimestamp("pronto_em")?.toLocalDateTime(),
            finalizadoEm = rs.getTimestamp("finalizado_em")?.toLocalDateTime()
        )
    }

    private fun findClienteById(id: UUID): Cliente? {
        return jdbcClient.sql(
            """
            SELECT * FROM cliente WHERE id = :id
        """
        )
            .params(
                mapOf(
                    "id" to id
                )
            )
            .query { rs, _ ->
                Cliente(
                    id = UUID.fromString(rs.getString("id")),
                    nome = rs.getString("nome"),
                    email = rs.getString("email"),
                    cpf = rs.getString("cpf")
                )
            }.optional().orElse(null)
    }

    private fun findItensByPedidoId(pedidoId: UUID): List<ItemPedido> {
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