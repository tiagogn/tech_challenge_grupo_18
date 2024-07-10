package br.com.fiap.lanchonete.repository

import br.com.fiap.lanchonete.domain.Produto
import br.com.fiap.lanchonete.domain.Produto.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProdutoRepository : JpaRepository<Produto, UUID> {

    fun findByCategoria(categoria: Categoria): List<Produto>

}