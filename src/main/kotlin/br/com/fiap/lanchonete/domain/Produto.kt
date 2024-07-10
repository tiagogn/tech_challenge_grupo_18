package br.com.fiap.lanchonete.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.math.BigDecimal
import java.util.UUID

@Entity
data class Produto(
    @Id
    @Column(name = "id")
    val id: UUID,
    val nome: String,
    val preco: BigDecimal,
    val categoria: Categoria
) {
    enum class Categoria {
        LANCHE,
        ACOMPANHAMENTO,
        BEBIDA,
        SOBREMESA
    }
}