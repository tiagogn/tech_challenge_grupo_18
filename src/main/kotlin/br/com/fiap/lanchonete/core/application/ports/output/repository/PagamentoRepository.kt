package br.com.fiap.lanchonete.core.application.ports.output.repository

import br.com.fiap.lanchonete.core.domain.Pagamento

interface PagamentoRepository {
    fun save(pagamento: Pagamento)
}