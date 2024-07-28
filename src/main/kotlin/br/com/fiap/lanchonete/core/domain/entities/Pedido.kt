package br.com.fiap.lanchonete.core.domain.entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

@Entity
data class Pedido(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    val cliente: Cliente?,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    val itens: List<ItemPedido>,

    val total: BigDecimal = itens.sumOf { it.precoUnitario * it.quantidade.toBigDecimal() },

    @Enumerated(EnumType.STRING)
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