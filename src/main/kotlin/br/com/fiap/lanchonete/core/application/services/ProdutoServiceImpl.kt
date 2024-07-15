package br.com.fiap.lanchonete.core.application.services

import br.com.fiap.lanchonete.core.application.ports.input.ProdutoService
import br.com.fiap.lanchonete.core.domain.entities.Produto
import br.com.fiap.lanchonete.core.application.services.exceptions.ResourceNotFoundException
import br.com.fiap.lanchonete.core.domain.repository.ProdutoRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*

@Service
@Qualifier("produtoService")
class ProdutoServiceImpl(
    private val produtoRepository: ProdutoRepository
) : ProdutoService {

    override fun cadastrarProduto(produto: Produto): Produto {
        return produtoRepository.save(produto)
    }

    override fun atualizarProduto(produto: Produto): Produto {
        produtoRepository.findById(produto.id)
            .orElseThrow { ResourceNotFoundException("Produto não encontrado") }
        return produtoRepository.save(produto)
    }

    override fun deletarProduto(id: UUID) {
        val produto = produtoRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Produto não encontrado") }
        produtoRepository.delete(produto)
    }
}