package br.com.fiap.lanchonete.core.domain.entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.UUID

@Entity
data class Produto(
    @Id
    @Column(name = "id")
    val id: UUID,
    val nome: String,
    val preco: BigDecimal,
   @Enumerated(EnumType.STRING)
    val categoria: Categoria
) {
    enum class Categoria {
        LANCHE,
        ACOMPANHAMENTO,
        BEBIDA,
        SOBREMESA
    }
}