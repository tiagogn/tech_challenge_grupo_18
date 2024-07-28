package br.com.fiap.lanchonete.core.application.ports.output.gateway

import br.com.fiap.lanchonete.core.domain.entities.Pagamento

interface PagamentoGateway {
    fun checkout(pagamento: Pagamento): Pagamento
}