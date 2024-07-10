package br.com.fiap.lanchonete.controller

import br.com.fiap.lanchonete.controller.request.ProdutoRequest
import br.com.fiap.lanchonete.controller.request.toModel
import br.com.fiap.lanchonete.controller.request.toUpdate
import br.com.fiap.lanchonete.controller.response.ProdutoResponse
import br.com.fiap.lanchonete.service.ProdutoService
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