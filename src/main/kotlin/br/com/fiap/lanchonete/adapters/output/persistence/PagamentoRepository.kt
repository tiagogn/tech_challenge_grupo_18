package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.domain.entities.Pagamento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PagamentoRepository : JpaRepository<Pagamento, UUID> {
}