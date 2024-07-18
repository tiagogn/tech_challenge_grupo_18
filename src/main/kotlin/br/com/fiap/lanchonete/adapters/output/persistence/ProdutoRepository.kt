package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.domain.entities.Cliente
import br.com.fiap.lanchonete.core.domain.entities.Produto
import br.com.fiap.lanchonete.core.domain.entities.Produto.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProdutoRepository : JpaRepository<Produto, UUID> {

    @Query("SELECT * FROM produto c WHERE c.categoria = :categoria", nativeQuery = true)
    fun findByCategoria(categoria: String): List<Produto>

}