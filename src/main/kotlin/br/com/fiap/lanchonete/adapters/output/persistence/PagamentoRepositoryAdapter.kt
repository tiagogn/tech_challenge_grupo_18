package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.application.ports.output.repository.PagamentoRepository
import br.com.fiap.lanchonete.core.domain.FormaPagamento
import br.com.fiap.lanchonete.core.domain.Pagamento
import br.com.fiap.lanchonete.core.domain.StatusPagamento
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Qualifier("pagamentoRepository")
class PagamentoRepositoryAdapter(
    private val jdbcClient: JdbcClient,
) : PagamentoRepository {

    override fun save(pagamento: Pagamento) {
        if (pagamento.id == null) {
            pagamento.id = UUID.randomUUID()
            insert(pagamento)
        }
    }

    override fun findById(id: UUID?): Optional<Pagamento> {
        if (id == null) {
            return Optional.empty()
        }
        return jdbcClient.sql(
            """
            SELECT * FROM pagamento WHERE id = :id
        """
        )
            .params(
                mapOf(
                    "id" to id
                )
            )
            .query { rs, _ -> mapRowPagamento(rs) }
            .optional()
    }

    private fun mapRowPagamento(rs: java.sql.ResultSet): Pagamento {
        return Pagamento(
            id = UUID.fromString(rs.getString("id")),
            valor = rs.getBigDecimal("valor"),
            status = StatusPagamento.valueOf(rs.getString("status")),
            formaPagamento = FormaPagamento.valueOf(rs.getString("forma_pagamento")),
            dataPagamento = rs.getTimestamp("data_pagamento").toLocalDateTime()
        )
    }

    private fun insert(pagamento: Pagamento) {
        jdbcClient.sql(
            """
            INSERT INTO pagamento (id, valor, status, forma_pagamento, data_pagamento)
            VALUES (:id, :valor, :status, :forma_pagamento, :data_pagamento)
        """
        )
            .params(mapParams(pagamento))
            .update()
    }

    private fun mapParams(pagamento: Pagamento): Map<String, Any?> {
        return mapOf(
            "id" to pagamento.id,
            "valor" to pagamento.valor,
            "status" to pagamento.status.name,
            "forma_pagamento" to pagamento.formaPagamento.name,
            "data_pagamento" to pagamento.dataPagamento
        )
    }

}