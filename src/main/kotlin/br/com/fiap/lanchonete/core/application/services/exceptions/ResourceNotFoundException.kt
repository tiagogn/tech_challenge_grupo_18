package br.com.fiap.lanchonete.core.application.services.exceptions

class ResourceNotFoundException(
    override val message: String
): RuntimeException() {
}