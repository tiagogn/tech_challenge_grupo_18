package br.com.fiap.lanchonete.core.application.ports.output

import br.com.fiap.lanchonete.core.domain.entities.Produto
import br.com.fiap.lanchonete.core.domain.entities.Produto.Categoria

interface ProdutoRepository {

    fun save(produto: Produto): Produto
    fun findById(id: Long): Produto?
    fun findAll(): List<Produto>
    fun deleteById(id: Long)
    fun findByCategoria(categoria: Categoria): List<Produto>
}