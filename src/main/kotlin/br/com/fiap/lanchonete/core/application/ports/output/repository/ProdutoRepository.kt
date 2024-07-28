package br.com.fiap.lanchonete.core.application.ports.output.repository

import br.com.fiap.lanchonete.core.domain.entities.CategoriaProduto
import br.com.fiap.lanchonete.core.domain.entities.Produto
import java.util.*

interface ProdutoRepository {
    fun findByCategoria(categoriaProduto: CategoriaProduto): List<Produto>
    abstract fun save(produto: Produto): Produto
    fun findById(id: UUID): Optional<Produto>
    abstract fun delete(produto: Produto)
}