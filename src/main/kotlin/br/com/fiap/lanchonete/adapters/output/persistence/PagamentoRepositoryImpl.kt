package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.application.ports.output.repository.PagamentoRepository
import br.com.fiap.lanchonete.core.domain.entities.Pagamento
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Qualifier("pagamentoRepository")
interface PagamentoRepositoryImpl : PagamentoRepository, JpaRepository<Pagamento, UUID> {
}