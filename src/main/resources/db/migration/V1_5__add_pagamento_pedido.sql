create table pagamento
(
    id              uuid primary key,
    pedido_id       uuid           not null,
    valor           numeric(10, 2) not null,
    status          text           not null,
    forma_pagamento text           not null,
    transacao_id    uuid,
    data_pagamento  timestamp
);

alter table pagamento
    add constraint fk_pagamento_pedido
        foreign key (pedido_id) references pedido (id);
