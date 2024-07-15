package br.com.fiap.lanchonete.adapters.input.rest

import br.com.fiap.lanchonete.adapters.input.rest.request.ProdutoRequest
import br.com.fiap.lanchonete.adapters.input.rest.request.toModel
import br.com.fiap.lanchonete.adapters.input.rest.request.toUpdate
import br.com.fiap.lanchonete.adapters.input.rest.response.ProdutoResponse
import br.com.fiap.lanchonete.core.application.ports.input.ProdutoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/v1/produtos")
class ProdutoController(
    private val produtoService: ProdutoService
) {
    @PostMapping
    fun cadastrarProduto(
        @RequestBody produtoRequest: ProdutoRequest
    ): ProdutoResponse {
        val produto = produtoService.cadastrarProduto(produtoRequest.toModel())
        return ProdutoResponse(
            id = produto.id.toString(),
            nome = produto.nome,
            preco = produto.preco,
            categoria = produto.categoria
        )
    }

    @PutMapping("/{id}")
    fun atualizarProduto(
        @PathVariable id: UUID,
        @RequestBody produtoRequest: ProdutoRequest
    ): ProdutoResponse {
        val produto = produtoService.atualizarProduto(produtoRequest.toUpdate(id))
        return ProdutoResponse(
            id = produto.id.toString(),
            nome = produto.nome,
            preco = produto.preco,
            categoria = produto.categoria
        )
    }

    @DeleteMapping("/{id}")
    fun deletarProduto(
        @PathVariable id: UUID
    ): ResponseEntity<Unit> {
        produtoService.deletarProduto(id)
        return ResponseEntity.ok().build()
    }
}