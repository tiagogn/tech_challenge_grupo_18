package br.com.fiap.lanchonete.core.exceptions

class PagamentoException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}