package br.com.fiap.lanchonete.core.application.services

import br.com.fiap.lanchonete.core.application.ports.input.PagamentoService
import br.com.fiap.lanchonete.core.application.ports.output.repository.PedidoRepository
import br.com.fiap.lanchonete.core.domain.Pagamento
import br.com.fiap.lanchonete.core.domain.StatusPagamento
import br.com.fiap.lanchonete.core.dto.PagamentoInput
import br.com.fiap.lanchonete.core.exceptions.PagamentoException
import java.time.LocalDateTime

class PagamentoServiceImpl(
    private val pedidoRepository: PedidoRepository,
) : PagamentoService {
    override fun confirmarPagamento(pagamentoInput: PagamentoInput): Unit {

        val pedido = pedidoRepository.findById(pagamentoInput.pedidoId).orElseThrow {
            throw IllegalArgumentException("Pedido não encontrado")
        }

        if (pedido.total > pagamentoInput.valor)
            throw PagamentoException("Valor do pagamento menor que o valor do pedido")

        val pagamento = Pagamento(
            valor = pedido.total,
            status = pagamentoInput.status,
            formaPagamento = pagamentoInput.formaPagamento,
            dataPagamento = LocalDateTime.now()
        )

        if (pagamentoInput.status == StatusPagamento.APROVADO)
            pagamento.pagamentoAprovado()
        else
            pagamento.pagamentoRecusado()

        pedido.pagamento = pagamento

        pedidoRepository.save(pedido)
    }
}