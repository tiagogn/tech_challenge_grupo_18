package br.com.fiap.lanchonete.adapters.input.rest.request

import br.com.fiap.lanchonete.core.domain.entities.CategoriaProduto
import br.com.fiap.lanchonete.core.domain.entities.Produto
import java.math.BigDecimal
import java.util.*

data class ProdutoRequest(
    var id: UUID? = null,
    val nome: String,
    val preco: BigDecimal,
    val categoria: CategoriaProduto
)

fun ProdutoRequest.toModel(): Produto {
    return Produto(
        id = this.id,
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