package br.com.fiap.lanchonete.adapters.input.rest.response

import br.com.fiap.lanchonete.core.domain.StatusPagamento
import java.math.BigDecimal
import java.util.UUID

data class PagamentoResponse(
    val pedidoId: String,
    val valor: BigDecimal,
    val status: StatusPagamento,
    val transactionId: UUID? = null
)