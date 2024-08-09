package br.com.fiap.lanchonete.core.domain.entities

import java.math.BigDecimal
import java.util.*

data class Produto(

    var id: UUID? = null,

    val nome: String,

    val preco: BigDecimal,

    val categoria: CategoriaProduto
)

enum class CategoriaProduto {
    LANCHE,
    ACOMPANHAMENTO,
    BEBIDA,
    SOBREMESA
}