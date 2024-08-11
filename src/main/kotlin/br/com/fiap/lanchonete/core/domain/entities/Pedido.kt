package br.com.fiap.lanchonete.core.domain.entities

import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

data class Pedido(

    var id: UUID? = null,

    val cliente: Cliente?,

    val itens: List<ItemPedido>,

    val total: BigDecimal = BigDecimal.ZERO,

    var status: StatusPedido = StatusPedido.AGUARDANDO_PAGAMENTO,

    val criadoEm: LocalDateTime = LocalDateTime.now(),

    var atualizadoEm: LocalDateTime = LocalDateTime.now(),

    var prontoEm: LocalDateTime? = null,

    var finalizadoEm: LocalDateTime? = null,
) {

    fun pagamentoAprovado() {
        if (status == StatusPedido.AGUARDANDO_PAGAMENTO) {
            status = StatusPedido.RECEBIDO
            atualizadoEm = LocalDateTime.now()
        }
    }

    fun pedidoEmPreparacao() {
        if (status == StatusPedido.RECEBIDO) {
            status = StatusPedido.EM_PREPARACAO
            atualizadoEm = LocalDateTime.now()
        }
    }

    fun pedidoPronto() {
        if (status == StatusPedido.EM_PREPARACAO) {
            status = StatusPedido.PRONTO
            atualizadoEm = LocalDateTime.now()
            prontoEm = LocalDateTime.now()
        }
    }

    fun pedidoFinalizado() {
        if (status == StatusPedido.PRONTO) {
            status = StatusPedido.FINALIZADO
            atualizadoEm = LocalDateTime.now()
            finalizadoEm = LocalDateTime.now()
        }
    }

    fun tempoEspera(): String {
        return if (status == StatusPedido.PRONTO) {
            val tempoEspera = Duration.between(criadoEm, prontoEm)
            "${tempoEspera.toMinutes()} minutos"
        } else {
            "0 minutos"
        }
    }
}

enum class StatusPedido {
    AGUARDANDO_PAGAMENTO, RECEBIDO, EM_PREPARACAO, PRONTO, FINALIZADO
}