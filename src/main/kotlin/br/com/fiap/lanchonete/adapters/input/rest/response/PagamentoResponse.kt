package br.com.fiap.lanchonete.adapters.input.rest.response

import br.com.fiap.lanchonete.core.domain.StatusPagamento
import java.math.BigDecimal

data class PagamentoResponse(
    val pedidoId: String,
    val codigo: Long,
    val valor: BigDecimal,
    val status: StatusPagamento,
)