package br.com.fiap.lanchonete.service

import br.com.fiap.lanchonete.domain.Produto
import java.util.*

interface ProdutoService {
    fun cadastrarProduto(produto: Produto): Produto
    fun atualizarProduto(produto: Produto): Produto
    fun deletarProduto(id: UUID)
}