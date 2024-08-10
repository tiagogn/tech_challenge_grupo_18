package br.com.fiap.lanchonete.core.application.ports.input

import br.com.fiap.lanchonete.core.domain.entities.CategoriaProduto
import br.com.fiap.lanchonete.core.domain.entities.Produto
import java.util.UUID

interface ProdutoService {
    fun cadastrarProduto(produto: Produto): Produto
    fun atualizarProduto(produto: Produto): Produto
    fun deletarProduto(id: UUID)
    fun findByCategoria(categoriaProduto: CategoriaProduto): List<Produto>
}