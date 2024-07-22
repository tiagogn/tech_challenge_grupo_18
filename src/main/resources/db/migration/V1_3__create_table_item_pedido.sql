CREATE TABLE IF NOT EXISTS item_pedido (
    id UUID PRIMARY KEY,
    pedido_id UUID,
    produto_id UUID,
    nome_produto VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(19, 2) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    CONSTRAINT fk_pedido
        FOREIGN KEY (pedido_id)
        REFERENCES Pedido(id),
    CONSTRAINT fk_produto
        FOREIGN KEY (produto_id)
        REFERENCES Produto(id)
);