package br.com.fiap.lanchonete.core.domain

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class Pagamento(

    var id: UUID? = null,

    val valor: BigDecimal,

    var status: StatusPagamento,

    val formaPagamento: FormaPagamento,

    var dataPagamento: LocalDateTime? = null
) {

    fun pagamentoAprovado() {
        if (status == StatusPagamento.PENDENTE) {
            status = StatusPagamento.APROVADO
        }
    }

    fun pagamentoRecusado() {
        if (status == StatusPagamento.PENDENTE) {
            status = StatusPagamento.RECUSADO
        }
    }
}

enum class StatusPagamento {
    PENDENTE, APROVADO, RECUSADO
}

enum class FormaPagamento {
    CARTAO_CREDITO, CARTAO_DEBITO, DINHEIRO, PIX, VR
}
