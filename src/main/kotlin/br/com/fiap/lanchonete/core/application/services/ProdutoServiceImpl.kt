package br.com.fiap.lanchonete.core.application.services

import br.com.fiap.lanchonete.core.application.ports.input.ProdutoService
import br.com.fiap.lanchonete.core.application.ports.output.repository.ProdutoRepository
import br.com.fiap.lanchonete.core.application.services.exceptions.ResourceNotFoundException
import br.com.fiap.lanchonete.core.domain.entities.CategoriaProduto
import br.com.fiap.lanchonete.core.domain.entities.Produto
import java.util.*

class ProdutoServiceImpl(
    private val produtoRepository: ProdutoRepository
) : ProdutoService {

    override fun cadastrarProduto(produto: Produto): Produto {
        return produtoRepository.save(produto)
    }

    override fun atualizarProduto(produto: Produto): Produto {
        produtoRepository.findById(produto.id!!)
            .orElseThrow { ResourceNotFoundException("Produto não encontrado") }
        return produtoRepository.save(produto)
    }

    override fun deletarProduto(id: UUID) {
        val produto = produtoRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Produto não encontrado") }
        produtoRepository.delete(produto)
    }

    override fun findByCategoria(categoriaProduto: CategoriaProduto): List<Produto> {
        return produtoRepository.findByCategoria(categoriaProduto)

    }

}