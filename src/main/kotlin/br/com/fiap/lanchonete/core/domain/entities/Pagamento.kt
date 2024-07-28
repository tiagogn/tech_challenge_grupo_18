package br.com.fiap.lanchonete.core.domain.entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity(name = "pagamento")
data class Pagamento(
    @Id
    @GeneratedValue(generator = "UUID")
    val id: UUID? = null,

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    val pedido: Pedido,

    val valor: BigDecimal,

    @Enumerated(EnumType.STRING)
    var status: StatusPagamento,

    @Enumerated(EnumType.STRING)
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
