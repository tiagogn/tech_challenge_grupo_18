package br.com.fiap.lanchonete.controller.request

import br.com.fiap.lanchonete.domain.Produto
import br.com.fiap.lanchonete.domain.Produto.Categoria
import java.math.BigDecimal
import java.util.UUID

data class ProdutoRequest(
    val nome: String,
    val preco: BigDecimal,
    val categoria: Categoria
)

fun ProdutoRequest.toModel(): Produto {
    return Produto(
        id = UUID.randomUUID(),
        nome = this.nome,
        preco = this.preco,
        categoria = this.categoria
    )
}

fun ProdutoRequest.toUpdate(id: UUID): Produto {
    return Produto(
        id = id,
        nome = this.nome,
        preco = this.preco,
        categoria = this.categoria
    )
}