package br.com.fiap.lanchonete.adapters.config

import br.com.fiap.lanchonete.core.application.ports.input.ClienteService
import br.com.fiap.lanchonete.core.application.ports.input.PagamentoService
import br.com.fiap.lanchonete.core.application.ports.input.PedidoService
import br.com.fiap.lanchonete.core.application.ports.input.ProdutoService
import br.com.fiap.lanchonete.core.application.ports.output.repository.ClienteRepository
import br.com.fiap.lanchonete.core.application.ports.output.repository.PedidoRepository
import br.com.fiap.lanchonete.core.application.ports.output.repository.ProdutoRepository
import br.com.fiap.lanchonete.core.application.services.ClienteServiceImpl
import br.com.fiap.lanchonete.core.application.services.PagamentoServiceImpl
import br.com.fiap.lanchonete.core.application.services.PedidoServiceImpl
import br.com.fiap.lanchonete.core.application.services.ProdutoServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConfigBeans(
    private val clienteRepository: ClienteRepository,
    private val pedidoRepository: PedidoRepository,
    private val produtoRepository: ProdutoRepository,
) {

    @Bean
    fun clienteService(): ClienteService {
        return ClienteServiceImpl(clienteRepository)
    }

    @Bean
    fun pagamentoService(): PagamentoService {
        return PagamentoServiceImpl(pedidoRepository)
    }

    @Bean
    fun pedidoService(): PedidoService {
        return PedidoServiceImpl(pedidoRepository, clienteRepository, produtoRepository)
    }

    @Bean
    fun produtoService(): ProdutoService {
        return ProdutoServiceImpl(produtoRepository)
    }
}