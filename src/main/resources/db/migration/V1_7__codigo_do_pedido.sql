delete
from pagamento;
delete
from item_pedido;
delete
from pedido;

alter table pedido
    add column codigo integer not null;

create sequence pedido_codigo_seq start 100 increment 1;