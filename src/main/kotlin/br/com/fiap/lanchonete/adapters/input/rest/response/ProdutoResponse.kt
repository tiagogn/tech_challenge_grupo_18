package br.com.fiap.lanchonete.adapters.input.rest.response

import br.com.fiap.lanchonete.core.domain.entities.Produto.Categoria
import java.math.BigDecimal

data class ProdutoResponse(
    val id: String,
    val nome: String,
    val preco: BigDecimal,
    val categoria: Categoria
)