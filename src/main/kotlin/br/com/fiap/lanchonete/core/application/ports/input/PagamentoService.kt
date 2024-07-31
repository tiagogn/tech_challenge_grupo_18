package br.com.fiap.lanchonete.core.application.ports.input

import br.com.fiap.lanchonete.core.domain.entities.FormaPagamento
import br.com.fiap.lanchonete.core.domain.entities.Pagamento
import java.util.*

interface PagamentoService {
    fun checkout(pedidoId: UUID, formaPagamento: FormaPagamento): Pagamento
}