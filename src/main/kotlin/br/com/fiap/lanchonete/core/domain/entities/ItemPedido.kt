package br.com.fiap.lanchonete.core.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import java.util.*

@Entity
data class ItemPedido(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID? = null,

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    val produto: Produto,

    val nomeProduto: String,

    val quantidade: Int,

    val precoUnitario: BigDecimal,

    val categoria: String
)
