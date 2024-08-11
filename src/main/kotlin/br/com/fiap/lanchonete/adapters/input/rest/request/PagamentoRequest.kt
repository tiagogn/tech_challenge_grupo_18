package br.com.fiap.lanchonete.adapters.input.rest.request

import br.com.fiap.lanchonete.core.domain.FormaPagamento
import java.math.BigDecimal
import java.util.UUID

data class PagamentoRequest(
    val pedidoId: UUID,
    val valor: BigDecimal,
    val formaPagamento: FormaPagamento
) {

}

