package br.com.fiap.lanchonete.core.application.services

import br.com.fiap.lanchonete.core.application.ports.input.PedidoService
import br.com.fiap.lanchonete.core.application.ports.output.repository.ClienteRepository
import br.com.fiap.lanchonete.core.application.ports.output.repository.PedidoRepository
import br.com.fiap.lanchonete.core.application.ports.output.repository.ProdutoRepository
import br.com.fiap.lanchonete.core.application.services.exceptions.ResourceNotFoundException
import br.com.fiap.lanchonete.core.domain.ItemPedido
import br.com.fiap.lanchonete.core.domain.Pedido
import br.com.fiap.lanchonete.core.domain.StatusPedido
import br.com.fiap.lanchonete.core.dto.PedidoOutput
import br.com.fiap.lanchonete.core.dto.PedidoStatusOutput
import java.util.*

class PedidoServiceImpl(
    private val pedidoRepository: PedidoRepository,
    private val clienteRepository: ClienteRepository,
    private val produtoRepository: ProdutoRepository
) : PedidoService {

    override fun criarPedido(clienteId: UUID?, itens: List<ItemPedido>): Pedido {
        val cliente =
            clienteId?.let {
                clienteRepository.findById(it).orElseThrow { throw ResourceNotFoundException("Cliente não encontrado") }
            }

        itens.forEach {
            it.produto.id?.let { it1 ->
                produtoRepository.findById(it1)
                    .orElseThrow { throw ResourceNotFoundException("Produto ${it.nomeProduto} não encontrado") }
            }
        }

        val pedido = Pedido(
            cliente = cliente,
            itens = itens,
            total = itens.sumOf { it.precoUnitario * it.quantidade.toBigDecimal() },
        )

        pedidoRepository.save(pedido)

        return pedido
    }

    override fun atualizarStatusPedido(pedidoId: UUID, novoStatus: StatusPedido): Pedido {

        val pedido =
            pedidoRepository.findById(pedidoId).orElseThrow { ResourceNotFoundException("Pedido não encontrado") }

        when (novoStatus) {
            StatusPedido.EM_PREPARACAO -> {
                pedido.pedidoEmPreparacao()
            }

            StatusPedido.PRONTO -> {
                pedido.pedidoPronto()
            }

            StatusPedido.FINALIZADO -> {
                pedido.pedidoFinalizado()
            }

            else -> {
                throw ResourceNotFoundException("Status inválido")
            }
        }

        pedidoRepository.save(pedido)
        return pedido
    }

    override fun listarPedidosAgrupadosPorStatus(): List<PedidoStatusOutput> {

        val findByStatusAgrupado =
            pedidoRepository.findAllByOrderByStatusNotIn(StatusPedido.FINALIZADO).groupBy { it.status }

        return findByStatusAgrupado.map {
            PedidoStatusOutput(
                status = it.key.name,
                pedidos = it.value.map { pedido ->
                    PedidoOutput(
                        id = pedido.id.toString(),
                        codigo = pedido.codigo!!,
                        valor = pedido.total,
                        status = pedido.status.name,
                        criadoEm = pedido.criadoEm,
                        clienteId = pedido.cliente?.id.toString()
                    )
                }.toList()
            )
        }
    }

    override fun buscarPorId(pedidoId: UUID): Pedido {
        return pedidoRepository.findById(pedidoId).orElseThrow { ResourceNotFoundException("Pedido não encontrado") }
    }

    override fun enviandoPedidoParaCozinha(pedidoId: UUID): Pedido {
        val pedido =
            pedidoRepository.findById(pedidoId).orElseThrow { ResourceNotFoundException("Pedido não encontrado") }
        pedido.pedidoEmPreparacao()
        pedidoRepository.save(pedido)
        return pedido
    }

    override fun pedidoPronto(pedidoId: UUID): Pedido {
        val pedido =
            pedidoRepository.findById(pedidoId).orElseThrow { ResourceNotFoundException("Pedido não encontrado") }
        pedido.pedidoPronto()
        pedidoRepository.save(pedido)
        return pedido
    }

    override fun pedidoFinalizado(pedidoId: UUID): Pedido {
        val pedido =
            pedidoRepository.findById(pedidoId).orElseThrow { ResourceNotFoundException("Pedido não encontrado") }
        pedido.pedidoFinalizado()
        pedidoRepository.save(pedido)
        return pedido
    }

}
