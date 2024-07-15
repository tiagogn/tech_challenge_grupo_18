package br.com.fiap.lanchonete.core.domain.repository

import br.com.fiap.lanchonete.core.domain.entities.Produto
import br.com.fiap.lanchonete.core.domain.entities.Produto.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProdutoRepository : JpaRepository<Produto, UUID> {

    fun findByCategoria(categoria: Categoria): List<Produto>

}