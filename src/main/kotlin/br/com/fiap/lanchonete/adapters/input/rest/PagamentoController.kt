package br.com.fiap.lanchonete.adapters.input.rest

import br.com.fiap.lanchonete.adapters.input.rest.request.RecebePagamentoRequest
import br.com.fiap.lanchonete.adapters.input.rest.response.PagamentoResponse
import br.com.fiap.lanchonete.core.application.ports.input.PagamentoService
import br.com.fiap.lanchonete.core.application.ports.input.PedidoService
import br.com.fiap.lanchonete.core.domain.StatusPagamento
import br.com.fiap.lanchonete.core.dto.PagamentoInput
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1/pagamento")
class PagamentoController(
    private val pagamentoService: PagamentoService,
    private val pedidoService: PedidoService
) {
    @PostMapping("/webhook")
    fun receberPagamento(@RequestBody recebePagamentoRequest: RecebePagamentoRequest): ResponseEntity<Unit> {

        pagamentoService.confirmarPagamento(
            PagamentoInput(
                pedidoId = recebePagamentoRequest.pedidoId,
                valor = recebePagamentoRequest.valor,
                formaPagamento = recebePagamentoRequest.formaPagamento,
                status = StatusPagamento.valueOf(recebePagamentoRequest.status)
            )
        )

        return ResponseEntity.ok().build()
    }

    @GetMapping("/pedido")
    fun buscarPagamentoPorPedidoId(@RequestParam(required = true) pedidoId: UUID): ResponseEntity<PagamentoResponse> {
        val pedido = pedidoService.buscarPorId(pedidoId)
        return pedido.pagamento?.let {
            ResponseEntity.ok(
                PagamentoResponse(
                    pedidoId = pedido.id.toString(),
                    codigo = pedido.codigo!!,
                    valor = it.valor,
                    status = it.status,
                )
            )
        } ?: ResponseEntity.notFound().build()
    }

}