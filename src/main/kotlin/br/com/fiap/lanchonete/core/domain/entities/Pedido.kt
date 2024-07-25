package br.com.fiap.lanchonete.core.domain.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
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
    val status: StatusPedido = StatusPedido.RECEBIDO,

    val criadoEm: LocalDateTime = LocalDateTime.now(),

    val atualizadoEm: LocalDateTime = LocalDateTime.now(),

    val prontoEm: LocalDateTime = LocalDateTime.now(),

    val finalizadoEm: LocalDateTime = LocalDateTime.now(),

    val tempoEspera: String

) {
    enum class StatusPedido {
        RECEBIDO,
        EM_PREPARACAO,
        PRONTO,
        FINALIZADO
    }
}
