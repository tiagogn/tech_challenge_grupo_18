package br.com.fiap.lanchonete.core.domain.entities

import java.math.BigDecimal
import java.util.*

data class ItemPedido(

    var id: UUID? = null,

    val produto: Produto,

    val nomeProduto: String,

    val quantidade: Int,

    val precoUnitario: BigDecimal,

    val categoria: String
) {

    fun getSubTotal(): BigDecimal {
        return precoUnitario * quantidade.toBigDecimal()
    }
}
