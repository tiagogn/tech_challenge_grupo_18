package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.application.ports.output.repository.PagamentoRepository
import br.com.fiap.lanchonete.core.domain.entities.Pagamento
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
@Qualifier("pagamentoRepository")
class PagamentoRepositoryAdapter : PagamentoRepository {
    override fun save(pagamento: Pagamento) {
        TODO("Not yet implemented")
    }
}