package br.com.fiap.lanchonete.core.application.services

import br.com.fiap.lanchonete.core.domain.entities.Pedido
import br.com.fiap.lanchonete.adapters.output.persistence.ClienteRepository
import br.com.fiap.lanchonete.adapters.output.persistence.PedidoRepository
import br.com.fiap.lanchonete.core.application.ports.input.PedidoService
import br.com.fiap.lanchonete.core.domain.entities.ItemPedido
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.time.Duration
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
            total = itens.sumOf { it.precoUnitario * it.quantidade.toBigDecimal() },
            tempoEspera = "0"

        )
        return pedidoRepository.save(pedido)
    }

    override fun atualizarStatusPedido(pedidoId: UUID, novoStatus: Pedido.StatusPedido): Pedido {
        val pedido = pedidoRepository.findById(pedidoId).orElseThrow { RuntimeException("Pedido não encontrado") }
        var pedidoAtualizado = pedido

        if (novoStatus.name.equals("PRONTO")) {
            pedidoAtualizado = pedido.copy(status = novoStatus, atualizadoEm = LocalDateTime.now(), prontoEm = LocalDateTime.now())
            pedidoRepository.save(pedidoAtualizado)
            pedidoAtualizado =  pedido.copy(tempoEspera = tempoEspera(pedidoId))
        }else if(novoStatus.name.equals("FINALIZADO")){
            pedidoAtualizado = pedido.copy(status = novoStatus, atualizadoEm = LocalDateTime.now(), finalizadoEm = LocalDateTime.now())
        }else{
            pedidoAtualizado = pedido.copy(status = novoStatus, atualizadoEm = LocalDateTime.now())
        }

        return pedidoRepository.save(pedidoAtualizado)
    }

    override fun tempoEspera(pedidoId: UUID): String {
        val pedido = pedidoRepository.findById(pedidoId).orElseThrow { RuntimeException("Pedido não encontrado") }

        val tempo_espera = Duration.between(pedido.criadoEm, pedido.prontoEm)


        if (tempo_espera.toMinutes() > 0) {

            return tempo_espera.toMinutes().toString() + "min"

        }else if(tempo_espera.toSeconds() > 0){

            return tempo_espera.toSeconds().toString() + "s"

        }else{
            return tempo_espera.toDays().toString() + "d"
        }
    }

    override fun listarPedidosNaFila(): List<Pedido> {
        return pedidoRepository.findByStatusNotOrderByCriadoEmAsc(Pedido.StatusPedido.FINALIZADO)
    }

    override fun historicoPedido(pedidoId: UUID): Pedido {
        return pedidoRepository.findById(pedidoId).orElseThrow { RuntimeException("Pedido não encontrado") }
    }

}
