package br.com.fiap.lanchonete.adapters.output.persistence

import br.com.fiap.lanchonete.core.application.ports.output.repository.ClienteRepository
import br.com.fiap.lanchonete.core.domain.entities.Cliente
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Qualifier("clienteRepository")
class ClienteRepositoryAdapter(
    private val jdbcClient: JdbcClient
) : ClienteRepository {
    override fun save(cliente: Cliente): Cliente {
        if (cliente.id == null) {
            persist(cliente)
        } else {
            update(cliente)
        }
        return cliente
    }

    private fun persist(cliente: Cliente) {
        val keyHolder: KeyHolder = GeneratedKeyHolder()
        jdbcClient.sql(
            """
            INSERT INTO cliente (id, nome, email, cpf)
            VALUES (:id, :nome, :email, :cpf)
        """
        )
            .params(
                mapOf(
                    "id" to cliente.id.toString(),
                    "nome" to cliente.nome,
                    "email" to cliente.email,
                    "cpf" to cliente.cpf
                )
            )
            .update(keyHolder)

        cliente.id = UUID.fromString(keyHolder.key?.toString())
    }

    private fun update(cliente: Cliente) {
        jdbcClient.sql(
            """
            UPDATE cliente
            SET nome = :nome, email = :email, cpf = :cpf
            WHERE id = :id
        """
        )
            .params(
                mapOf(
                    "id" to cliente.id.toString(),
                    "nome" to cliente.nome,
                    "email" to cliente.email,
                    "cpf" to cliente.cpf
                )
            )
            .update()
    }

    override fun findByCPF(cpf: String): Cliente? {
        return jdbcClient.sql("SELECT * FROM cliente WHERE cpf = :cpf")
            .params(mapOf("cpf" to cpf))
            .query { rs, _ ->
                if (rs.next()) {
                    return@query Cliente(
                        id = UUID.fromString(rs.getString("id")),
                        nome = rs.getString("nome"),
                        email = rs.getString("email"),
                        cpf = rs.getString("cpf")
                    )
                }
                return@query null
            }
            .single()
    }

    override fun findById(id: UUID): Cliente? {
        return jdbcClient.sql("SELECT * FROM cliente WHERE id = :id")
            .params(mapOf("id" to id.toString()))
            .query { rs, _ ->
                if (rs.next()) {
                    return@query Cliente(
                        id = UUID.fromString(rs.getString("id")),
                        nome = rs.getString("nome"),
                        email = rs.getString("email"),
                        cpf = rs.getString("cpf")
                    )
                }
                return@query null
            }
            .single()
    }
}