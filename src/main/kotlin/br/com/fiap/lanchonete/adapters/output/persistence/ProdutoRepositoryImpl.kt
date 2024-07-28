package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.application.ports.output.repository.ProdutoRepository
import br.com.fiap.lanchonete.core.domain.entities.CategoriaProduto
import br.com.fiap.lanchonete.core.domain.entities.Produto
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Qualifier("produtoRepository")
interface ProdutoRepositoryImpl : ProdutoRepository, JpaRepository<Produto, UUID> {

    @Query("SELECT p FROM Produto p WHERE p.categoria = :categoriaProduto")
    override fun findByCategoria(categoriaProduto: CategoriaProduto): List<Produto>

}