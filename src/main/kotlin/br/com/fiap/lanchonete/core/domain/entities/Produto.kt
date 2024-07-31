package br.com.fiap.lanchonete.core.domain.entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
data class Produto(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val nome: String,

    val preco: BigDecimal,

    @Enumerated(EnumType.STRING)
    val categoria: CategoriaProduto
)

enum class CategoriaProduto {
    LANCHE,
    ACOMPANHAMENTO,
    BEBIDA,
    SOBREMESA
}