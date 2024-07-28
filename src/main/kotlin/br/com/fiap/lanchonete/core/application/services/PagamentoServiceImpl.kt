package br.com.fiap.lanchonete.core.application.services

import br.com.fiap.lanchonete.adapters.output.persistence.PagamentoRepository
import br.com.fiap.lanchonete.adapters.output.persistence.PedidoRepository
import br.com.fiap.lanchonete.core.application.ports.input.PagamentoService
import br.com.fiap.lanchonete.core.application.ports.output.gateway.PagamentoGateway
import br.com.fiap.lanchonete.core.domain.entities.FormaPagamento
import br.com.fiap.lanchonete.core.domain.entities.Pagamento
import br.com.fiap.lanchonete.core.domain.entities.StatusPagamento
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*

@Service
@Qualifier("pagamentoService")
class PagamentoServiceImpl(
    private val pedidoRepository: PedidoRepository,
    private val pagamentoRepository: PagamentoRepository,
    private val pagamentoGateway: PagamentoGateway
) : PagamentoService {
    override fun checkout(pedidoId: UUID, formaPagamento: FormaPagamento): Pagamento {

        val pedido = pedidoRepository.findById(pedidoId).orElseThrow {
            throw IllegalArgumentException("Pedido não encontrado")
        }

        if (pedido.total <= 0.toBigDecimal()) {
            throw IllegalArgumentException("Pedido sem valor")
        }

        val pagamento = Pagamento(
            pedido = pedido,
            valor = pedido.total,
            status = StatusPagamento.PENDENTE,
            formaPagamento = formaPagamento
        )

        pagamentoGateway.checkout(pagamento)
        pagamentoRepository.save(pagamento)

        if (pagamento.status == StatusPagamento.APROVADO) {
            pagamento.pedido.pagamentoAprovado()
            pedidoRepository.save(pagamento.pedido)
        }

        return pagamento
    }
}