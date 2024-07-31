alter table pedido
    drop column tempo_espera;
alter table pedido
    alter column pronto_em drop not null;
alter table pedido
    alter column finalizado_em drop not null;