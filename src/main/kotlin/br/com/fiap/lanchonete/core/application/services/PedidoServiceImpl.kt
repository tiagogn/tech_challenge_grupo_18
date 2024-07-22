package br.com.fiap.lanchonete.core.application.services

import br.com.fiap.lanchonete.core.domain.entities.Pedido
import br.com.fiap.lanchonete.adapters.output.persistence.ClienteRepository
import br.com.fiap.lanchonete.adapters.output.persistence.PedidoRepository
import br.com.fiap.lanchonete.core.application.ports.input.PedidoService
import br.com.fiap.lanchonete.core.domain.entities.ItemPedido
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
@Qualifier("pedidoService")
class PedidoServiceImpl(
    private val pedidoRepository: PedidoRepository,
    private val clienteRepository: ClienteRepository
) : PedidoService {

    override fun criarPedido(clienteId: UUID?, itens: List<ItemPedido>): Pedido {
        val cliente =
            clienteId?.let { clienteRepository.findById(it).orElseThrow { RuntimeException("Cliente não encontrado") } }
        val pedido = Pedido(
            cliente = cliente,
            itens = itens,
            total = itens.sumOf { it.precoUnitario * it.quantidade.toBigDecimal() }
        )
        return pedidoRepository.save(pedido)
    }

    override fun atualizarStatusPedido(pedidoId: UUID, novoStatus: Pedido.StatusPedido): Pedido {
        val pedido = pedidoRepository.findById(pedidoId).orElseThrow { RuntimeException("Pedido não encontrado") }
        val pedidoAtualizado = pedido.copy(status = novoStatus, atualizadoEm = LocalDateTime.now())
        /* if (novoStatus == StatusPedido.PRONTO) {
             notificacaoService.notificarCliente(pedidoAtualizado)
         } */
        return pedidoRepository.save(pedidoAtualizado)
    }

    override fun listarPedidosNaFila(): List<Pedido> {
        return pedidoRepository.findByStatusNotOrderByCriadoEmAsc(Pedido.StatusPedido.FINALIZADO)
    }
}
