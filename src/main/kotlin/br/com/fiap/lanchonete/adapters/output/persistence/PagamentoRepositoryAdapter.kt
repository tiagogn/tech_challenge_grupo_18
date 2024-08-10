package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.application.ports.output.repository.PagamentoRepository
import br.com.fiap.lanchonete.core.domain.entities.Pagamento
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
@Qualifier("pagamentoRepository")
class PagamentoRepositoryAdapter(
    private val jdbcClient: JdbcClient
) : PagamentoRepository {

    override fun save(pagamento: Pagamento) {
        if (pagamento.id == null) {
            pagamento.id = UUID.randomUUID()
            insert(pagamento)
        } else {
            update(pagamento)
        }
    }

    private fun insert(pagamento: Pagamento) {
        jdbcClient.sql(
            """
            INSERT INTO pagamento (id, pedido_id, valor, status, forma_pagamento, transacao_id, data_pagamento)
            VALUES (:id, :pedido_id, :valor, :status, :forma_pagamento, :transacao_id, :data_pagamento)
        """
        )
            .params(mapParams(pagamento))
            .update()
    }

    private fun update(pagamento: Pagamento) {
        jdbcClient.sql(
            """
            UPDATE pagamento
            SET pedido_id = :pedido_id, valor = :valor, status = :status, forma_pagamento = :forma_pagamento, transacao_id = :transacao_id, data_pagamento = :data_pagamento
            WHERE id = :id
        """
        )
            .params(mapParams(pagamento))
            .update()
    }

    private fun mapParams(pagamento: Pagamento): Map<String, Any?> {
        return mapOf(
            "id" to pagamento.id,
            "pedido_id" to pagamento.pedido.id,
            "valor" to pagamento.valor,
            "status" to pagamento.status.name,
            "forma_pagamento" to pagamento.formaPagamento.name,
            "transacao_id" to pagamento.transactionId,
            "data_pagamento" to pagamento.dataPagamento
        )
    }
}