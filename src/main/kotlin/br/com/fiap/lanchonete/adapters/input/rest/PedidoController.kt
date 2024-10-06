package br.com.fiap.lanchonete.adapters.input.rest

import br.com.fiap.lanchonete.adapters.input.rest.request.PedidoRequest
import br.com.fiap.lanchonete.adapters.input.rest.request.toModel
import br.com.fiap.lanchonete.adapters.input.rest.response.AtualizacaoPedidoStatusResponse
import br.com.fiap.lanchonete.adapters.input.rest.response.PedidoResponse
import br.com.fiap.lanchonete.adapters.input.rest.response.PedidoStatusResponse
import br.com.fiap.lanchonete.adapters.input.rest.response.toResponse
import br.com.fiap.lanchonete.core.application.ports.input.PedidoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1/pedidos")
class PedidoController(
    private val pedidoService: PedidoService
) {

    @PostMapping
    fun criarPedido(@RequestBody(required = true) request: PedidoRequest): ResponseEntity<PedidoResponse> {
        val itens = request.itens.map { it.toModel() }
        val pedido = pedidoService.criarPedido(request.clienteId, itens)
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido.toResponse())
    }

    @PatchMapping("/{pedidoId}/preparacao")
    fun pedidoEmPreparacao(
        @PathVariable(required = true) pedidoId: UUID,
    ): ResponseEntity<AtualizacaoPedidoStatusResponse> {
        val pedido = pedidoService.enviandoPedidoParaCozinha(pedidoId)
        val response = AtualizacaoPedidoStatusResponse(
            pedidoId = pedido.id.toString(),
            statusPedido = pedido.status,
            mensagem = "Pedido enviado para cozinha"
        )
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @PatchMapping("/{pedidoId}/pronto")
    fun pedidoPronto(
        @PathVariable(required = true) pedidoId: UUID,
    ): ResponseEntity<AtualizacaoPedidoStatusResponse> {
        val pedido = pedidoService.pedidoPronto(pedidoId)
        val response = AtualizacaoPedidoStatusResponse(
            pedidoId = pedido.id.toString(),
            statusPedido = pedido.status,
            mensagem = "Pedido pronto para retirada"
        )
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @PatchMapping("/{pedidoId}/finalizado")
    fun pedidoFinalizado(
        @PathVariable(required = true) pedidoId: UUID,
    ): ResponseEntity<AtualizacaoPedidoStatusResponse> {
        val pedido = pedidoService.pedidoFinalizado(pedidoId)
        val response = AtualizacaoPedidoStatusResponse(
            pedidoId = pedido.id.toString(),
            statusPedido = pedido.status,
            mensagem = "Pedido finalizado e entregue"
        )
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("")
    fun listarPedidos(): ResponseEntity<List<PedidoResponse>> {
        val pedidos = pedidoService.listarPedidoOrdenadoPorStatus()
        return ResponseEntity.status(HttpStatus.OK).body(pedidos.map { it.toResponse() })
    }

    @GetMapping("/{pedidoId}")
    fun buscarPorId(@PathVariable(required = true) pedidoId: UUID): PedidoResponse {
        val pedido = pedidoService.buscarPorId(pedidoId)
        return PedidoResponse(
            id = pedido.id,
            cliente = pedido.cliente?.toResponse(),
            itens = pedido.itens.map { it.toResponse() },
            total = pedido.total,
            status = pedido.status,
            criadoEm = pedido.criadoEm,
            atualizadoEm = pedido.atualizadoEm,
            tempoEspera = pedido.tempoEspera(),
            codigo = pedido.codigo!!
        )
    }

    @GetMapping("/{pedidoId}/status")
    fun buscarStatus(@PathVariable(required = true) pedidoId: UUID): PedidoStatusResponse {
        val pedido = pedidoService.buscarPorId(pedidoId)
        return PedidoStatusResponse(
            status = pedido.status,
            codigo = pedido.codigo!!,
            pagamento = pedido.pagamento?.status.toString()
        )
    }

}
