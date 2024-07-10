package br.com.fiap.lanchonete.service

import br.com.fiap.lanchonete.domain.Produto
import br.com.fiap.lanchonete.exceptions.ResourceNotFoundException
import br.com.fiap.lanchonete.repository.ProdutoRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.jpa.domain.AbstractPersistable_.id
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