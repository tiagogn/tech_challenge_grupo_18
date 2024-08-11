package br.com.fiap.lanchonete.core.application.ports.output.gateway

import br.com.fiap.lanchonete.core.domain.Pagamento

interface PagamentoGateway {
    fun checkout(pagamento: Pagamento): Pagamento
}