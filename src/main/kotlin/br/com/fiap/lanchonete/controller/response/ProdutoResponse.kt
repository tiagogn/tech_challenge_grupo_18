package br.com.fiap.lanchonete.controller.response

import br.com.fiap.lanchonete.domain.Produto.Categoria
import java.math.BigDecimal

data class ProdutoResponse(
    val id: String,
    val nome: String,
    val preco: BigDecimal,
    val categoria: Categoria
)