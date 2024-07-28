package br.com.fiap.lanchonete.core.application.ports.output.repository

import br.com.fiap.lanchonete.core.domain.entities.CategoriaProduto
import br.com.fiap.lanchonete.core.domain.entities.Produto

interface ProdutoRepository {
    fun findByCategoria(categoriaProduto: CategoriaProduto): List<Produto>
}