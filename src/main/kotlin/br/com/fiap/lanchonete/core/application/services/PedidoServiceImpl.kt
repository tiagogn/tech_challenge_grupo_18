package br.com.fiap.lanchonete.core.application.services

import br.com.fiap.lanchonete.core.application.ports.input.PedidoService
import br.com.fiap.lanchonete.core.application.ports.output.repository.ClienteRepository
import br.com.fiap.lanchonete.core.application.ports.output.repository.PedidoRepository
import br.com.fiap.lanchonete.core.application.ports.output.repository.ProdutoRepository
import br.com.fiap.lanchonete.core.application.services.exceptions.ResourceNotFoundException
import br.com.fiap.lanchonete.core.domain.entities.ItemPedido
import br.com.fiap.lanchonete.core.domain.entities.Pedido
import br.com.fiap.lanchonete.core.domain.entities.StatusPedido
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
        return pedidoRepository.save(pedido)
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

        return pedidoRepository.save(pedido)
    }

    override fun listarPedidosPorStatus(statusPedido: StatusPedido): List<Pedido> {
        return pedidoRepository.findByStatus(statusPedido)
    }

    override fun buscarPorId(pedidoId: UUID): Pedido {
        return pedidoRepository.findById(pedidoId).orElseThrow { ResourceNotFoundException("Pedido não encontrado") }
    }

    override fun enviandoPedidoParaCozinha(pedidoId: UUID): Pedido {
        val pedido =
            pedidoRepository.findById(pedidoId).orElseThrow { ResourceNotFoundException("Pedido não encontrado") }
        pedido.pedidoEmPreparacao()
        return pedidoRepository.save(pedido)
    }

    override fun pedidoPronto(pedidoId: UUID): Pedido {
        val pedido =
            pedidoRepository.findById(pedidoId).orElseThrow { ResourceNotFoundException("Pedido não encontrado") }
        pedido.pedidoPronto()
        return pedidoRepository.save(pedido)
    }

    override fun pedidoFinalizado(pedidoId: UUID): Pedido {
        val pedido =
            pedidoRepository.findById(pedidoId).orElseThrow { ResourceNotFoundException("Pedido não encontrado") }
        pedido.pedidoFinalizado()
        return pedidoRepository.save(pedido)
    }
}
