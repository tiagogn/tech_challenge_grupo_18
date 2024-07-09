package br.com.fiap.lanchonete.exceptions

class ResourceNotFoundException(
    override val message: String
): RuntimeException() {
}