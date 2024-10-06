package br.com.fiap.lanchonete.core.application.ports.input

import br.com.fiap.lanchonete.core.dto.PagamentoInput

interface PagamentoService {
    fun confirmarPagamento(pagamentoInput: PagamentoInput): Unit
}