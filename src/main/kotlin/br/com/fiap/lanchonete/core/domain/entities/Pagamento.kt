package br.com.fiap.lanchonete.core.domain.entities

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class Pagamento(

    val id: UUID? = null,

    val pedido: Pedido,

    val valor: BigDecimal,

    var status: StatusPagamento,

    val formaPagamento: FormaPagamento,

    var transactionId: UUID? = null,

    var dataPagamento: LocalDateTime? = null
) {

}

enum class StatusPagamento {
    PENDENTE, APROVADO, REJEITADO
}

enum class FormaPagamento {
    CARTAO_CREDITO, CARTAO_DEBITO, DINHEIRO, PIX, CHEQUE
}
