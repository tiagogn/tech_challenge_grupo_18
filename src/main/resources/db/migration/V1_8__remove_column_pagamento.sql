alter table pagamento
    drop column transacao_id;

alter table pagamento
    drop column pedido_id;

alter table pedido
    add column pagamento_id uuid;