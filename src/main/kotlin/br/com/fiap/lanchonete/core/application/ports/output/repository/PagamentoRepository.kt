package br.com.fiap.lanchonete.core.application.ports.output.repository

import br.com.fiap.lanchonete.core.domain.Pagamento
import java.util.*

interface PagamentoRepository {
    fun save(pagamento: Pagamento)
    fun findById(id: UUID?): Optional<Pagamento>
}