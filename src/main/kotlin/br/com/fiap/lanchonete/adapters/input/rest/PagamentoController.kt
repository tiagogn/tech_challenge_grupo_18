package br.com.fiap.lanchonete.adapters.input.rest

import br.com.fiap.lanchonete.adapters.input.rest.request.PagamentoRequest
import br.com.fiap.lanchonete.adapters.input.rest.response.PagamentoResponse
import br.com.fiap.lanchonete.core.application.ports.input.PagamentoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/pagamento")
class PagamentoController(
    private val pagamentoService: PagamentoService
) {

    @PostMapping
    fun efetuarPagamento(@RequestBody pagamentoRequest: PagamentoRequest): PagamentoResponse {

        val pagamento = pagamentoService.checkout(pagamentoRequest.pedidoId, pagamentoRequest.formaPagamento)

        return PagamentoResponse(
            pedidoId = pagamento.pedido.id.toString(),
            valor = pagamento.valor,
            status = pagamento.status,
            transactionId = pagamento.transactionId
        )
    }
}