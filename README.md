# Projeto da lanchonete Grupo 18 Pós-Tech - FIAP

![Java](https://img.shields.io/badge/Java-17-red)
![SpringBoot](https://img.shields.io/badge/SpringBoot-3.1-green)
![Docker](https://img.shields.io/badge/Docker-20.10-blue)
![Postgres](https://img.shields.io/badge/Postgres-16-blue)

O objetivo do projeto é disponibilizar APIs para os seguintes serviços da lanchonete:

- cadastro de cliente
- identificação do cliente via CPF
- criar, editar e remover produtos
- buscar produtos por categoria
- Fake checkout, apenas enviar os produtos escolhidos para a fila. O checkout é a finalização do pedido.
- listar pedidos

O propjeto foi desenvolvido utilizando as seguintes tecnologias:

- java 17+
- springboot 3.1+
- docker
- banco de dados postgres 16

## Executando o projeto localmente

Baixe o projeto no seguinte endereço:

- https://github.com/tiagogn/tech_challenge_grupo_18

em seguinte, dentro da pasta do projeto execute o comando

```idea .```

com isso, o projeto será aberto dentro da IDE **Intellij**

Para rodar o projeto através do Docker Compose, utilize o seguinte comando:

```
docker-compose -f docker-compose.yaml up -d
```

ou execute o seguinte comando na pasta do projeto:

```
./gradlew bootRun
```

## Swagger

* API (http://localhost:8080/lanchonete/swagger-ui/index.html)

## Postman

Dentro do projeto, existe uma collection do Postman com os endpoints para teste.

* Collection (lanchonete/Tech Challenge.postman_collection.json)

Em cada endpoint, existe um exemplo de requisição para teste.

Ordem de execução dos endpoints:

1. POST Produto/Cadastrar Produto
2. POST Cliente/Cadastrar Cliente(opcional)
3. POST Pedido/Cadastrar Pedido
4. POST Pagamento/Webhook
5. PATCH Pedido/Pedido em Preparação
6. PATCH Pedido/Pedido Pronto
7. PATCH Pedido/Pedido Finalizado
8. GET Pedido/Listar Pedido

## Kubernetes

Para rodar o projeto no Kubernetes, utilize o seguinte comando:

```
kubectl apply -f devops/
```

## Arquitetura

## Link do Miro

## Fase I
