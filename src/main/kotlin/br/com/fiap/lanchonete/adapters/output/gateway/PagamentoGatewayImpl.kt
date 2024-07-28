package br.com.fiap.lanchonete.adapters.output.gateway

import br.com.fiap.lanchonete.core.application.ports.output.gateway.PagamentoGateway
import br.com.fiap.lanchonete.core.domain.entities.FormaPagamento
import br.com.fiap.lanchonete.core.domain.entities.Pagamento
import br.com.fiap.lanchonete.core.domain.entities.StatusPagamento
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
@Qualifier("pagamentoGateway")
class PagamentoGatewayImpl : PagamentoGateway {
    override fun checkout(pagamento: Pagamento): Pagamento {

        if (pagamento.valor <= 0.toBigDecimal() || pagamento.formaPagamento == FormaPagamento.CHEQUE) {
            pagamento.status = StatusPagamento.REJEITADO
            return pagamento
        }

        pagamento.status = StatusPagamento.APROVADO
        pagamento.transactionId = java.util.UUID.randomUUID()
        pagamento.dataPagamento = java.time.LocalDateTime.now()
        return pagamento
    }
}