package br.com.fiap.lanchonete.core.dto

import br.com.fiap.lanchonete.core.domain.FormaPagamento
import br.com.fiap.lanchonete.core.domain.StatusPagamento
import java.math.BigDecimal
import java.util.*

data class PagamentoInput(
    val pedidoId: UUID,
    val valor: BigDecimal,
    val formaPagamento: FormaPagamento,
    val status: StatusPagamento,
)