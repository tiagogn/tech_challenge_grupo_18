package br.com.fiap.lanchonete.adapters.input.rest

import br.com.fiap.lanchonete.adapters.input.rest.request.AtualizarStatusRequest
import br.com.fiap.lanchonete.adapters.input.rest.request.PedidoRequest
import br.com.fiap.lanchonete.adapters.input.rest.request.toModel
import br.com.fiap.lanchonete.adapters.input.rest.response.PedidoResponse
import br.com.fiap.lanchonete.adapters.input.rest.response.toResponse
import br.com.fiap.lanchonete.core.application.ports.input.PedidoService
import br.com.fiap.lanchonete.core.domain.entities.Pedido
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/pedidos")
class PedidoController(
    private val pedidoService: PedidoService
) {

    @PostMapping
    fun criarPedido(@RequestBody request: PedidoRequest): ResponseEntity<PedidoResponse> {
        val itens = request.itens.map { it.toModel() }
        val pedido = pedidoService.criarPedido(request.clienteId, itens)
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido.toResponse())
    }

    @PatchMapping("/{pedidoId}/status")
    fun atualizarStatusPedido(
        @PathVariable pedidoId: UUID,
        @RequestBody request: AtualizarStatusRequest
    ): ResponseEntity<PedidoResponse> {
        val pedidoAtualizado = pedidoService.atualizarStatusPedido(pedidoId, request.novoStatus)
        return ResponseEntity.status(HttpStatus.OK).body(pedidoAtualizado.toResponse())
    }

    @PostMapping("/{pedidoId}/checkout")
    fun finalizarPedido(@PathVariable pedidoId: UUID): ResponseEntity<PedidoResponse> {
        val pedidoFinalizado = pedidoService.atualizarStatusPedido(pedidoId, Pedido.StatusPedido.FINALIZADO)
        return ResponseEntity.status(HttpStatus.OK).body(pedidoFinalizado.toResponse())
    }

    @GetMapping("/fila")
    fun listarPedidosNaFila(): ResponseEntity<List<PedidoResponse>> {
        val pedidosNaFila = pedidoService.listarPedidosNaFila()
        return ResponseEntity.status(HttpStatus.OK).body(pedidosNaFila.map { it.toResponse() })
    }
}
