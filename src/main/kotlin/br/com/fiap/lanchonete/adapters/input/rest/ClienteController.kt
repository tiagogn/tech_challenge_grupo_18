package br.com.fiap.lanchonete.adapters.input.rest

import br.com.fiap.lanchonete.adapters.input.rest.request.ClienteRequest
import br.com.fiap.lanchonete.adapters.input.rest.response.ClienteResponse
import br.com.fiap.lanchonete.core.application.ports.inputs.ClienteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/clientes")
class ClienteController(
    private val clienteService: ClienteService
) {
    @PostMapping
    @ResponseBody
    fun cadastrarCliente(@RequestBody clienteRequest: ClienteRequest): ClienteResponse {
        val cliente = clienteService.cadastrarCliente(clienteRequest.toModel())
        return ClienteResponse(
            identificador = cliente.identificador.toString(),
            nome = cliente.nome,
            email = cliente.email,
            cpf = cliente.cpf
        )
    }

    @GetMapping("/cpf/{cpf}")
    @ResponseBody
    fun buscarClientePorCPF(@PathVariable cpf: String): ClienteResponse {
        val cliente = clienteService.buscarClientePorCPF(cpf)
        return ClienteResponse(
            identificador = cliente.identificador.toString(),
            nome = cliente.nome,
            email = cliente.email,
            cpf = cliente.cpf
        )
    }
}