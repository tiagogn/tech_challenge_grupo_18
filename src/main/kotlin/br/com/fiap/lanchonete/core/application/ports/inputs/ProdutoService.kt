package br.com.fiap.lanchonete.core.application.ports.inputs

import br.com.fiap.lanchonete.core.domain.entities.Produto
import java.util.*

interface ProdutoService {
    fun cadastrarProduto(produto: Produto): Produto
    fun atualizarProduto(produto: Produto): Produto
    fun deletarProduto(id: UUID)
}